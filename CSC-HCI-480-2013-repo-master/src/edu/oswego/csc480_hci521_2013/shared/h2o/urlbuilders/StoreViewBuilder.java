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
        if (view < 0 || view > 1024) {
            throw new IllegalArgumentException("value must be between 1 and 1024 (inclusive)");
        }
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
        if (offset < 0 || offset > 1024) {
            throw new IllegalArgumentException("value must be between 1 and 1024 (inclusive)");
        }
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
