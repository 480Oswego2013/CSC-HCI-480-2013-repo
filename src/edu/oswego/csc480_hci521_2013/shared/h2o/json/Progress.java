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
package edu.oswego.csc480_hci521_2013.shared.h2o.json;

/**
 * Represents the H2O Progress json response.
 * @see edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.ProgressBuilder
 */
public class Progress extends AbstractResponse {

    /**
     * The name of the data that progress is being checked.
     */
    private String destinationKey = null;

    /**
     * No arg constructor needed for GWT-RPC.
     */
    private Progress() {
    }

    /**
     *
     * @return the name of the data being checked
     */
    public String getDestinationKey() {
        return destinationKey;
    }

    @Override
    public String toString() {
        return "Progress{" + "destination_key=" + destinationKey
                + super.toString() + '}';
    }
}
