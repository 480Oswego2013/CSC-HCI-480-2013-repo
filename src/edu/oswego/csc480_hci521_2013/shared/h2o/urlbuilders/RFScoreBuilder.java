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

import java.util.HashMap;
import java.util.Map;

/**
 * URL Builder for H2O RFScore Request.
 */
public class RFScoreBuilder extends AbstractBuilder {

    /**
     * api endpoint name.
     */
    static final String NAME = "RFScore";

    /**
     * Constructor used by RedirectRequestFactory.
     * @param args hash of args from the response
     * @see RedirectRequestFactory
     */
    RFScoreBuilder(final HashMap<String, String> args) {
        super(NAME);
        if (args == null) {
            throw new IllegalArgumentException("args can not be null");
        }
        setArgs(args);
    }

    /**
     * default constructor.
     * @param dataKey An existing H2O HEX key
     * @param modelKey Key of the RF model
     */
    public RFScoreBuilder(final String dataKey, final String modelKey) {
        super(NAME);
        if (dataKey == null) {
            throw new IllegalArgumentException("data key can not be null");
        }
        if (modelKey == null) {
            throw new IllegalArgumentException("model key can not be null");
        }
        addArg("data_key", dataKey);
        addArg("model_key", modelKey);
    }

    /**
     * @param value A column name. The output classification (also known as
     * 'response variable') that is being learned.
     * @return this
     */
    public RFScoreBuilder setResponseVariable(final String value) {
        if (value == null) {
            throw new IllegalArgumentException("response variable can not be null");
        }
        addArg("response_variable", value);
        return this;
    }

    /**
     * @param value Integer from 0 to 2147483647
     * @return this
     */
    public RFScoreBuilder setNtree(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be positive");
        }
        addArg("ntree", Integer.toString(value));
        return this;
    }

    /**
     * @param values Category weight (positive)
     * @return this
     */
    public RFScoreBuilder setClassWeights(final HashMap<String, Double> values) {
        if (values == null) {
            throw new IllegalArgumentException("weights can not be null");
        }
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
     * @param value Do not produce confusion matrix
     * @return this
     */
    public RFScoreBuilder setNoConfusionMatrix(final boolean value) {
        addArg("no_confusion_matrix", value ? "1" : "0");
        return this;
    }

    /**
     * @param value Clear cache of model confusion matrices
     * @return this
     */
    public RFScoreBuilder setClearConfusionMatrix(final boolean value) {
        addArg("clear_confusion_matrix", value ? "1" : "0");
        return this;
    }
}
