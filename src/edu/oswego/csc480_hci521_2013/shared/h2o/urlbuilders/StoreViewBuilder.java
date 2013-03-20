package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.HashMap;

/**
 *
 */
public class StoreViewBuilder extends AbstractBuilder {

    static final String NAME = "StoreView";

    StoreViewBuilder(HashMap<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public StoreViewBuilder() {
        super(NAME);
    }

    /**
     *
     * @param view the number of items to view, used for pagination of data
     * @return
     */
    public StoreViewBuilder setView(Integer view) {
        addArg("view", view.toString());
        return this;
    }

    /**
     *
     * @param offset the offset into the data to return, used for pagination of
     * data
     * @return
     */
    public StoreViewBuilder setOffset(Integer offset) {
        addArg("offset", offset.toString());
        return this;
    }

    /**
     *
     * @param value a filter for what is returned, a simple string match is done
     * @return
     */
    public StoreViewBuilder setFilter(String value) {
        addArg("filter", value);
        return this;
    }
}
