package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.Map;

/**
 *
 */
public class ImportUrlBuilder extends AbstractBuilder {

    static final String NAME = "ImportUrl";

    ImportUrlBuilder() {
    }

    ImportUrlBuilder(Map<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public ImportUrlBuilder(String url) {
        super(NAME);
        addArg("url", url);
    }

    public ImportUrlBuilder setKey(String key) {
        addArg("key", key);
        return this;
    }
}
