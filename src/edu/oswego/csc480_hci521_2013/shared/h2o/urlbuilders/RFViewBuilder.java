package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import java.util.Map;

/**
 *
 */
public class RFViewBuilder extends AbstractBuilder {

    static final String NAME = "RFView";

    RFViewBuilder() {
    }

    RFViewBuilder(Map<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public RFViewBuilder(String dataKey, String modelKey) {
        super(NAME);
        addArg("data_key", dataKey);
        addArg("model_key", modelKey);
    }

    public RFViewBuilder setOutOfBagErrorEstimate(boolean value) {
        if (value) {
            addArg("out_of_bag_error_estimate", "true");
        } else {
            addArg("out_of_bag_error_estimate", "false");
        }
        return this;
    }

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
        addArg("ntree", value.toString());
        return this;
    }

    // TODO: class_weights. it is unclear if this argument does anything!
}
