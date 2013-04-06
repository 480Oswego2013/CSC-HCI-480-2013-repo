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

/**
 *
 */
public class InspectBuilder extends AbstractBuilder
{

    InspectBuilder()
    {
    }

    public InspectBuilder(String key)
    {
        super("Inspect.json");
        addArg("key", key);
    }

    /**
     *
     * @param view the number of items to view, used for pagination of parsed data
     * @return
     */
    public InspectBuilder setView(Integer view)
    {
        addArg("view", view.toString());
        return this;
    }

    /**
     *
     * @param offset the offset into the data to return, used for pagination of parsed data
     * @return
     */
    public InspectBuilder setOffset(Integer offset)
    {
        addArg("offset", offset.toString());
        return this;
    }
}
