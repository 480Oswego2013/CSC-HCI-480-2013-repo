/*
 * Copyright 2013 State University of New York at Oswego
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class RFViewBuilder extends AbstractBuilder {

    static final String NAME = "RFView";

    RFViewBuilder() {
    }

    RFViewBuilder(HashMap<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public RFViewBuilder(String dataKey, String modelKey) {
        super(NAME);
        addArg("data_key", dataKey);
        addArg("model_key", modelKey);
    }

    public RFViewBuilder(RF forest)
    {
        super("RFView.json");
        addArg("data_key", forest.getDataKey());
        addArg("model_key", forest.getModelKey());
        setResponseVariable(forest.getResponseVariable());
    }

    public RFViewBuilder setOutOfBagErrorEstimate(boolean value)
    {
        if (value) {
            addArg("out_of_bag_error_estimate", "true");
        } else {
            addArg("out_of_bag_error_estimate", "false");
        }
        return this;
    }

    /**
     * Column name.
     *
     * @param value
     * @return
     */
    public RFViewBuilder setResponseVariable(Integer value) {
        addArg("response_variable", value.toString());
        return this;
    }

    /**
     * NOTE: it is unclear if this actually does anything..
     *
     * @param value the number of trees
     * @return this
     */
    public RFViewBuilder setNtree(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be positive");
        }
        addArg("ntree", value.toString());
        return this;
    }

    /**
     * @param values Category weight (positive)
     * @return
     */
    public RFViewBuilder setClassWeights(HashMap<String, Double> values) {
        StringBuilder value = new StringBuilder();
        for (Map.Entry<String, Double> pair : values.entrySet()) {
            if (pair.getValue() < 0) {
                throw new IllegalArgumentException("values must be positive");
            }
            value.append(pair.getKey()).append('=')
                    .append(pair.getValue()).append(',');
        }
        addArg("class_weights",
                value.deleteCharAt(value.length() - 1).toString());
        return this;
    }

    /**
     * Do not produce confusion matrix.
     *
     * @param value
     * @return
     */
    public RFViewBuilder setNoConfusionMatrix(boolean value) {
        if (value) {
            addArg("no_confusion_matrix", "1");
        } else {
            addArg("no_confusion_matrix", "0");
        }
        return this;
    }

    /**
     * Clear cache of model confusion matrices.
     *
     * @param value
     * @return
     */
    public RFViewBuilder clearConfusionMatrixCache(boolean value) {
        if (value) {
            addArg("clear_confusion_matrix", "1");
        } else {
            addArg("clear_confusion_matrix", "0");
        }
        return this;
    }
}
