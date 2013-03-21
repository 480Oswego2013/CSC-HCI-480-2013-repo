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
        if (view < 1 || view > 10000) {
            throw new IllegalArgumentException("value must be between 1 and 10000 (inclusive)");
        }
        addArg("view", view.toString());
        return this;
    }

    /**
     *
     * @param offset the offset into the data to return, -1 to view only column info and no data
     * parsed data
     * @return
     */
    public InspectBuilder setOffset(Long offset) {
        if (offset < -1) {
            throw new IllegalArgumentException("value must be greater than -1");
        }
        addArg("offset", offset.toString());
        return this;
    }
}
