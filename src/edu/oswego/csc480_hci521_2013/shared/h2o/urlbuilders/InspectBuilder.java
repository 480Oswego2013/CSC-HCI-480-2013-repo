/*
 * Copyright 2013 State University of New York at Oswego
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
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
