package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.Map;

/**
 *
 */
public class ParseBuilder extends AbstractBuilder {

    static final String NAME = "Parse";

    ParseBuilder() {
    }

    ParseBuilder(Map<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public ParseBuilder(String sourceKey) {
        super(NAME);
        addArg("source_key", sourceKey);
    }

    public ParseBuilder setDestinationKey(String destinationKey) {
        addArg("destination_key", destinationKey);
        return this;
    }

    public ParseBuilder setHeader(boolean header) {
        if (header) {
            addArg("header", "1");
        } else {
            addArg("header", "0");
        }
        return this;
    }
}
