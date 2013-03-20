package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.HashMap;

/**
 *
 */
public class ProgressBuilder extends AbstractBuilder {

    static final String NAME = "Progress";

    ProgressBuilder() {
    }

    ProgressBuilder(HashMap<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public ProgressBuilder(String job, String destinationKey) {
        super(NAME);
        addArg("job", job);
        addArg("destination_key", destinationKey);
    }
}
