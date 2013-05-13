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

import com.google.gson.annotations.SerializedName;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Arrays;

/**
 * Represents the H2O StoreView json response.
 * @see edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.StoreViewBuilder
 */
public class StoreView extends AbstractResponse {

    private Row[] keys = null;
    private int numKeys = 0;
    private String cloudName = null;
    private String nodeName = null;

    /**
     * No arg constructor needed for GWT-RPC.
     */
    private StoreView() {
    }

    public Row[] getKeys() {
        return keys;
    }

    public int getNumKeys() {
        return numKeys;
    }

    public String getCloudName() {
        return cloudName;
    }

    public String getNodeName() {
        return nodeName;
    }

    @Override
    public String toString() {
        return "StoreView{" + "keys=" + Arrays.toString(keys)
                + ", num_keys=" + numKeys + ", cloud_name=" + cloudName
                + ", node_name=" + nodeName + super.toString() + '}';
    }

    public static class Row implements IsSerializable {

        private String key = null;
        private int valueSizeBytes = 0;
        private String rows = null; // not always a number
        private String cols = null; // not always a number
        @SerializedName("col_0")
        private Column col0 = null;
        @SerializedName("col_1")
        private Column col1 = null;
        @SerializedName("col_2")
        private Column col2 = null;
        @SerializedName("col_3")
        private Column col3 = null;
        @SerializedName("col_4")
        private Column col4 = null;
        private String value = null;

        /**
         * No arg constructor needed for GWT-RPC.
         */
        private Row() {
        }

        public String getKey() {
            return key;
        }

        public int getValueSizeBytes() {
            return valueSizeBytes;
        }

        public String getRows() {
            return rows;
        }

        public String getCols() {
            return cols;
        }

        public Column getCol0() {
            return col0;
        }

        public Column getCol1() {
            return col1;
        }

        public Column getCol2() {
            return col2;
        }

        public Column getCol3() {
            return col3;
        }

        public Column getCol4() {
            return col4;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Row{" + "key=" + key
                    + ", value_size_bytes=" + valueSizeBytes
                    + ", rows=" + rows + ", cols=" + cols + ", col_0=" + col0
                    + ", col_1=" + col1 + ", col_2=" + col2 + ", col_3="
                    + col3 + ", col_4=" + col4 + ", value=" + value + '}';
        }

        public static class Column implements IsSerializable {

            private String header = null;
            private String min = null;
            private String mean = null;
            private String max = null;

            /**
             * No arg constructor needed for GWT-RPC.
             */
            private Column() {
            }

            public String getHeader() {
                return header;
            }

            public String getMin() {
                return min;
            }

            public String getMean() {
                return mean;
            }

            public String getMax() {
                return max;
            }

            @Override
            public String toString() {
                return "Column{" + "header=" + header + ", min=" + min
                        + ", mean=" + mean + ", max=" + max + '}';
            }
        }
    }
}
