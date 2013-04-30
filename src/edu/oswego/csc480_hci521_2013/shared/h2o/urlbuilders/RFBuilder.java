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
import java.util.Map.Entry;

/**
 *
 */
public class RFBuilder extends AbstractBuilder {

    public static enum StatType {
        GINI,
        ENTROPY
    }

    public static enum SamplingStrategy {
        RANDOM,
        STRATIFIED_LOCAL
    }

    static final String NAME = "RF";

    RFBuilder() {
    }

    RFBuilder(HashMap<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public RFBuilder(String dataKey) {
        super(NAME);
        addArg("data_key", dataKey);
    }

    /**
     * @param value Column name	The output classification (also known as
     * 'response variable') that is being learned.
     * @return
     */
    public RFBuilder setResponseVariable(String value) {
        addArg("response_variable", value);
        return this;
    }

    /**
     * @param value Integer from 0 to 2147483647
     * @return
     */
    public RFBuilder setNtree(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be positive");
        }
        addArg("ntree", value.toString());
        return this;
    }

    /**
     * @param type the stat type to use.
     * @return this
     */
    public RFBuilder setStatType(final StatType type) {
        addArg("stat_type", type.name());
        return this;
    }

    /**
     * @param values Category weight (positive)
     * @return
     */
    public RFBuilder setClassWeights(HashMap<String, Double> values) {
        StringBuilder value = new StringBuilder();
        for (Entry<String, Double> pair : values.entrySet()) {
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
     * @param value the type of sampling to use
     * @return this
     */
    public RFBuilder setSamplingStrategy(SamplingStrategy value) {
        addArg("sampling_strategy", value.name());
        return this;
    }

    /**
     * @param values Category strata (integer)
     * @return
     */
    public RFBuilder setStrataSamples(HashMap<String, Integer> values)
    {
        StringBuilder value = new StringBuilder();
        for (Entry<String, Integer> pair : values.entrySet()) {
            value.append(pair.getKey()).append('=')
                    .append(pair.getValue()).append(',');
        }
        addArg("strata_samples", value.deleteCharAt(value.length() - 1).toString());
        return this;
    }

    /**
     * @param value Valid H2O key
     * @return
     */
    public RFBuilder setModelKey(String value) {
        addArg("model_key", value);
        return this;
    }

    /**
     * @param value Out of bag errors
     * @return
     */
    public RFBuilder setOutOfBagErrorEstimate(boolean value) {
        addArg("out_of_bag_error_estimate", value ? "1" : "0");
        return this;
    }

    /**
     * @param value Integer from 0 to 2147483647
     * @return
     */
    public RFBuilder setFeatures(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be positive");
        }
        addArg("features", value.toString());
        return this;
    }

    /**
     * can be used multiple times
     *
     * @param value Columns to select
     * @return
     */
    public RFBuilder setIgnore(Integer value) {
        addMultiArg("ignore", value.toString());
        return this;
    }

    /**
     * @param value Integer from 1 to 100
     * @return
     */
    public RFBuilder setSample(Integer value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("value must be between 1 and 100 (inclusive)");
        }
        addArg("sample", value.toString());
        return this;
    }

    /**
     * @param value Integer from 0 to 65535
     * @return
     */
    public RFBuilder setBinLimit(Integer value) {
        if (value < 0 || value > 65535) {
            throw new IllegalArgumentException("value must be between 0 and 65535 (inclusive)");
        }
        addArg("bin_limit", value.toString());
        return this;
    }

    /**
     * @param value Integer from 0 to 2147483647
     * @return
     */
    public RFBuilder setDepth(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be positive");
        }
        addArg("depth", value.toString());
        return this;
    }

    /**
     * @param value Integer value
     * @return
     */
    public RFBuilder setSeed(Integer value) {
        addArg("seed", value.toString());
        return this;
    }

    /**
     * @param value Build trees in parallel
     * @return
     */
    public RFBuilder setParallel(boolean value) {
        addArg("parallel", value ? "1" : "0");
        return this;
    }

    /**
     * This allows for the use of == conditionals in the trees and not just <=.
     * @param value Integer from 0 to 2147483647
     * @return
     */
    public RFBuilder setExclusiveSplitLimit(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be positive");
        }
        addArg("exclusive_split_limit", value.toString());
        return this;
    }
}
