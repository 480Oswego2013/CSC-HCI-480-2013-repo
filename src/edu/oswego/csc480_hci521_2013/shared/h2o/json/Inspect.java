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
import java.util.HashMap;

/**
 *
 */
public class Inspect extends AbstractResponse {

    private String type;
    private String key;
    private int num_rows;
    private int num_cols;
    private int row_size;
    private int value_size_bytes;
    private Column[] cols;
    private Row[] rows;

    private Inspect() {
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public int getNumRows() {
        return num_rows;
    }

    public int getNumCols() {
        return num_cols;
    }

    public int getRowSize() {
        return row_size;
    }

    public int getValueSizeBytes() {
        return value_size_bytes;
    }

    public Column[] getCols() {
        return cols;
    }

    public Row[] getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "Inspect{" + "type=" + type + ", key=" + key
                + ", num_rows=" + num_rows + ", num_cols=" + num_cols
                + ", row_size=" + row_size
                + ", value_size_bytes=" + value_size_bytes
                + ", cols=" + Arrays.toString(cols)
                + ", rows=" + Arrays.toString(rows) + super.toString() + '}';
    }

    public static class Column implements IsSerializable {

        private String name;
        private int offset;
        private int size;
        private int base;
        private int scale;
        private double min;
        private double max;
        private double mean;
        private double variance;
        private int num_missing_values;
        private String type;
        private int enum_domain_size;

        private Column() {
        }

        public String getName() {
            return name;
        }

        public int getOffset() {
            return offset;
        }

        public int getSize() {
            return size;
        }

        public int getBase() {
            return base;
        }

        public int getScale() {
            return scale;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public double getMean() {
            return mean;
        }

        public double getVariance() {
            return variance;
        }

        public int getNumMissingValues() {
            return num_missing_values;
        }

        public String getType() {
            return type;
        }

        public int getEnumDomainSize() {
            return enum_domain_size;
        }

        @Override
        public String toString() {
            return "Column{" + "name=" + name + ", offset=" + offset
                    + ", size=" + size + ", base=" + base + ", scale=" + scale
                    + ", min=" + min + ", max=" + max + ", mean=" + mean
                    + ", variance=" + variance
                    + ", num_missing_values=" + num_missing_values
                    + ", type=" + type
                    + ", enum_domain_size=" + enum_domain_size + '}';
        }
    }

    public static class Row implements IsSerializable {

        private int row;
        private HashMap<String, String> data;

        private Row() {
        }

        public Row(int row, HashMap<String, String> data) {
            this.row = row;
            this.data = data;
        }

        public int getRow() {
            return row;
        }

        public String getData(String name) {
            return data.get(name);
        }

        @Override
        public String toString() {
            return "InspectRow{" + "row=" + row + ", data=" + data + '}';
        }
    }
}
