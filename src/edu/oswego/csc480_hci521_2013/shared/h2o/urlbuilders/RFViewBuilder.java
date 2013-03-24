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
public class RFViewBuilder extends AbstractBuilder
{

    RFViewBuilder()
    {
    }

    public RFViewBuilder(String dataKey, String modelKey)
    {
        super("RFView.json");
        addArg("data_key", dataKey);
        addArg("model_key", modelKey);
    }

    public RFViewBuilder setOutOfBagErrorEstimate(boolean value)
    {
        if (value) {
            addArg("out_of_bag_error_estimate", "true");
        } else {
            addArg("out_of_bag_error_estimate", "false");
        }
        return this;
    }

    // TODO: ntree, response_variable, class_weights. it is unclear if these arguments do anything!
}
