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
 * Represents the H2O RF json response.
 * @see edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder
 */
public class RF extends AbstractResponse {

    /**
     * the name of the data.
     */
    private String dataKey = null;
    /**
     * the name of the model.
     */
    private String modelKey = null;
    /**
     * the number of trees.
     */
    private int ntree = 0;
    /**
     * the response variable used.
     */
    private int responseVariable = 0;
    /**
     * full scoring or out of bag errors
     */
    private boolean outOfBagErrorEstimate = false;

    public RF(String dataKey, String modelKey, int ntree, int responseVariable,
            boolean oobError) {
        this.dataKey = dataKey;
        this.modelKey = modelKey;
        this.ntree = ntree;
        this.responseVariable = responseVariable;
        this.outOfBagErrorEstimate = oobError;
    }

    /**
     * No arg constructor needed for GWT-RPC.
     */
    private RF() {
    }

    /**
     *
     * @return the data name
     */
    public String getDataKey() {
        return dataKey;
    }

    /**
     *
     * @return the model name
     */
    public String getModelKey() {
        return modelKey;
    }

    /**
     *
     * @return the number of trees
     */
    public int getNtree() {
        return ntree;
    }

    /**
     *
     * @return the response variable used
     */
    public int getResponseVariable() {
        return responseVariable;
    }

    /**
     *
     * @return true for out of bag errors, false for full scoring
     */
    public boolean isOutOfBagErrorEstimate() {
        return outOfBagErrorEstimate;
    }

    @Override
    public String toString() {
        return "RF{" + "data_key=" + dataKey + ", model_key=" + modelKey
                + ", ntree=" + ntree
                + ", response_variable=" + responseVariable
                + ", out_of_bag_error_estimate=" + outOfBagErrorEstimate
                + super.toString() + '}';
    }
}
