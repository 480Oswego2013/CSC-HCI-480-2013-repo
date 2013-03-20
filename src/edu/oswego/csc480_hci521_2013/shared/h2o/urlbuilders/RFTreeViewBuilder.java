package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.HashMap;

/**
 *
 */
public class RFTreeViewBuilder extends AbstractBuilder {

    static final String NAME = "RFTreeView";

    RFTreeViewBuilder() {
    }

    RFTreeViewBuilder(HashMap<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public RFTreeViewBuilder(String dataKey, String modelKey) {
        super(NAME);
        addArg("data_key", dataKey);
        addArg("model_key", modelKey);
    }

    public RFTreeViewBuilder setTreeNumber(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("tree number must be positive");
        }
        addArg("tree_number", Integer.valueOf(value).toString());
        return this;
    }

    public RFTreeViewBuilder setResponseVariable(int value) {
        addArg("response_variable", Integer.valueOf(value).toString());
        return this;
    }
}
