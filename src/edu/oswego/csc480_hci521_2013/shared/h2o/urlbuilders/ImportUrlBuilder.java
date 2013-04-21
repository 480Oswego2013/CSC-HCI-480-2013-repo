
package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.HashMap;

/**
 *
 */
public class ImportUrlBuilder extends AbstractBuilder {

    static final String NAME = "ImportUrl";

    ImportUrlBuilder() {
    }

    ImportUrlBuilder(HashMap<String, String> args) {
        super(NAME);
        setArgs(args);
    }

    public ImportUrlBuilder(String url) {
        super(NAME);
        addArg("url", url);
    }

    public ImportUrlBuilder setKey(String key) {
        // updated to protect against null
        if(key.equals("")){
            System.out.println("no keyValue");
            return null;
        }
        else{
        addArg("key", key);
        return this;
        }
    }
}
