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
 * Represents the H2O Inspect json response.
 * @see edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.InspectBuilder
 */
public class Inspect extends AbstractResponse {

    /**
     * The type of the data.
     */
    private String type = null;
    /**
     * The data key.
     */
    private String key = null;
    /**
     * The number of rows in the data.
     */
    private int numRows = 0;
    /**
     * The number of columns in the data.
     */
    private int numCols = 0;
    private int rowSize = 0;
    /**
     * How many bytes the data takes up.
     */
    private int valueSizeBytes = 0;
    /**
     * The column definitions.
     */
    private Column[] cols = null;
    /**
     * The rows of data.
     */
    private Row[] rows = null;

    /**
     * No arg constructor needed for GWT-RPC.
     */
    private Inspect() {
    }

    /**
     * @return The type of the data
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return The data key
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @return The number of rows in the data
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     *
     * @return The number of columns in the data
     */
    public int getNumCols() {
        return numCols;
    }

    public int getRowSize() {
        return rowSize;
    }

    /**
     *
     * @return The number of bytes the data takes up
     */
    public int getValueSizeBytes() {
        return valueSizeBytes;
    }

    /**
     *
     * @return The column definitions
     */
    public Column[] getCols() {
        return cols;
    }

    /**
     *
     * @return The rows of data
     */
    public Row[] getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "Inspect{" + "type=" + type + ", key=" + key
                + ", num_rows=" + numRows + ", num_cols=" + numCols
                + ", row_size=" + rowSize
                + ", value_size_bytes=" + valueSizeBytes
                + ", cols=" + Arrays.toString(cols)
                + ", rows=" + Arrays.toString(rows) + super.toString() + '}';
    }

    public static class Column implements IsSerializable {

        /**
         * the name of the column.
         */
        private String name = null;
        /**
         * the offset of the column.
         */
        private int offset = 0;
        private int size = 0;
        private int base = 0;
        private int scale = 0;
        /**
         * the minimum value of the column across rows.
         */
        private double min = 0;
        /**
         * the maximum value of the column across rows.
         */
        private double max = 0;
        /**
         * the average value of the column across rows.
         */
        private double mean = 0;
        /**
         * the value variance.
         */
        private double variance = 0;
        /**
         * the number of rows without this value.
         */
        private int numMissingValues = 0;
        /**
         * the type of the data in this column.
         */
        private String type = null;
        /**
         * the number of enum values if this is an enum.
         */
        private int enumDomainSize = 0;

        /**
         * No arg constructor needed for GWT-RPC.
         */
        private Column() {
        }

        /**
         * @return the name of the column.
         */
        public String getName() {
            return name;
        }

        /**
         * @return the offset of the column.
         */
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

        /**
         * @return the minimum value of the column across rows.
         */
        public double getMin() {
            return min;
        }

        /**
         * @return the maximum value of the column across rows.
         */
        public double getMax() {
            return max;
        }

        /**
         * @return the average value of the column across rows.
         */
        public double getMean() {
            return mean;
        }

        /**
         * @return the value variance.
         */
        public double getVariance() {
            return variance;
        }

        /**
         * @return the number of rows without this value.
         */
        public int getNumMissingValues() {
            return numMissingValues;
        }

        /**
         * @return the type of the data in this column.
         */
        public String getType() {
            return type;
        }

        /**
         * @return the number of enum values if this is an enum.
         */
        public int getEnumDomainSize() {
            return enumDomainSize;
        }

        @Override
        public String toString() {
            return "Column{" + "name=" + name + ", offset=" + offset
                    + ", size=" + size + ", base=" + base + ", scale=" + scale
                    + ", min=" + min + ", max=" + max + ", mean=" + mean
                    + ", variance=" + variance
                    + ", num_missing_values=" + numMissingValues
                    + ", type=" + type
                    + ", enum_domain_size=" + enumDomainSize + '}';
        }
    }

    public static class Row implements IsSerializable {

        /**
         * the index of this row.
         */
        private int row = 0;
        /**
         * the values, indexed by column name.
         */
        private HashMap<String, String> data = null;

        /**
         * No arg constructor needed for GWT-RPC.
         */
        private Row() {
        }

        public Row(int row, HashMap<String, String> data) {
            this.row = row;
            this.data = data;
        }

        /**
         *
         * @return the index of this row
         */
        public int getRow() {
            return row;
        }

        /**
         *
         * @param name A Column name
         * @return the value of the column
         */
        public String getData(String name) {
            return data.get(name);
        }

        @Override
        public String toString() {
            return "InspectRow{" + "row=" + row + ", data=" + data + '}';
        }
    }
}
