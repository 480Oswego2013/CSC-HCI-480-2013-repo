package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class StoreView extends H2OResponse {

    private Row[] keys;
    private int num_keys;
    private String cloud_name;
    private String node_name;

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
