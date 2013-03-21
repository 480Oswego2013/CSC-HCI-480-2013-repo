package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 */
public class RFBuilder extends AbstractBuilder {

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
     * @param value use gini statistic (otherwise entropy is used)
     * @return
     */
    public RFBuilder setGini(boolean value) {
        addArg("gini", value ? "1" : "0");
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
     * @param value Use Stratified sampling
     * @return
     */
    public RFBuilder setStratify(boolean value) {
        addArg("stratify", value ? "1" : "0");
        return this;
    }

    /**
     * @param values Category strata (integer)
     * @return
     */
    public RFBuilder setStrata(HashMap<String, Integer> values)
    {
        StringBuilder value = new StringBuilder();
        for (Entry<String, Integer> pair : values.entrySet()) {
            value.append(pair.getKey()).append('=')
                    .append(pair.getValue()).append(',');
        }
        addArg("strata", value.deleteCharAt(value.length() - 1).toString());
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
