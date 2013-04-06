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

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Arrays;

/**
 *
 */
public class StoreView extends AbstractResponse {

    private Row[] keys;
    private int num_keys;
    private String cloud_name;
    private String node_name;

    private StoreView() {
    }

    public Row[] getKeys() {
        return keys;
    }

    public int getNumKeys() {
        return num_keys;
    }

    public String getCloudName() {
        return cloud_name;
    }

    public String getNodeName() {
        return node_name;
    }

    @Override
    public String toString() {
        return "StoreView{" + "keys=" + Arrays.toString(keys)
                + ", num_keys=" + num_keys + ", cloud_name=" + cloud_name
                + ", node_name=" + node_name + super.toString() + '}';
    }

    public static class Row implements IsSerializable {

        private String key;
        private int value_size_bytes;
        private String rows; // not always a number
        private String cols; // not always a number
        private Column col_0;
        private Column col_1;
        private Column col_2;
        private Column col_3;
        private Column col_4;
        private String value;

        private Row() {
        }

        public String getKey() {
            return key;
        }

        public int getValueSizeBytes() {
            return value_size_bytes;
        }

        public String getRows() {
            return rows;
        }

        public String getCols() {
            return cols;
        }

        public Column getCol0() {
            return col_0;
        }

        public Column getCol1() {
            return col_1;
        }

        public Column getCol2() {
            return col_2;
        }

        public Column getCol3() {
            return col_3;
        }

        public Column getCol4() {
            return col_4;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Row{" + "key=" + key
                    + ", value_size_bytes=" + value_size_bytes
                    + ", rows=" + rows + ", cols=" + cols + ", col_0=" + col_0
                    + ", col_1=" + col_1 + ", col_2=" + col_2 + ", col_3="
                    + col_3 + ", col_4=" + col_4 + ", value=" + value + '}';
        }

        public static class Column implements IsSerializable {

            private String header;
            private String min;
            private String mean;
            private String max;

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
