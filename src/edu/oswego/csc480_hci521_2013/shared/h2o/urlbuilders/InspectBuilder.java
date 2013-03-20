package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.HashMap;

/**
 *
 */
public class InspectBuilder extends AbstractBuilder {

    static final String NAME = "Inspect";

    InspectBuilder() {
    }

    InspectBuilder(HashMap<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public InspectBuilder(String key) {
        super(NAME);
        addArg("key", key);
    }

    /**
     *
     * @param view the number of items to view, used for pagination of parsed
     * data
     * @return
     */
    public InspectBuilder setView(Integer view) {
        addArg("view", view.toString());
        return this;
    }

    /**
     *
     * @param offset the offset into the data to return, used for pagination of
     * parsed data
     * @return
     */
    public InspectBuilder setOffset(Integer offset) {
        addArg("offset", offset.toString());
        return this;
    }
}
