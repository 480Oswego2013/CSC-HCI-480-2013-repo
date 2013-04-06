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
import java.util.ArrayList;

/**
 *
 */
public class StoreView extends H2OResponse {

    private ArrayList<Row> keys;
    private int num_keys;
    private String cloud_name;
    private String node_name;

    public ArrayList<Row> getKeys() {
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
        return "StoreView{" + "keys=" + keys + ", num_keys=" + num_keys + ", cloud_name=" + cloud_name + ", node_name=" + node_name + " " + super.toString() + '}';
    }

    public static class Row implements IsSerializable {

        private String key;
        private int value_size_bytes;
        private String rows; // not always a number
        private String cols; // not always a number
        // TODO: implement this, these are the first 5 columns of the data, for sample display
        // col_0 { header, min, mean, max }
        // col_1
        // col_2
        // col_3
        // col_4
        private String value;

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

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Row{" + "key=" + key + ", value_size_bytes=" + value_size_bytes + ", rows=" + rows + ", cols=" + cols + ", value=" + value + '}';
        }
    }
}
