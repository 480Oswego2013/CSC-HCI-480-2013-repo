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
 * Represents the H2O RFView json response.
 * @see edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFViewBuilder
 */
public class RFView extends AbstractResponse {

    private String dataKey = null;
    private String modelKey = null;
    private int responseVariable = 0;
    private int ntree = 0;
    private int mtry = 0;
    private String confusionKey = null;
    private ConfusionMatrix confusionMatrix = null;
    private TreeProperties trees = null;

    /**
     * No arg constructor needed for GWT-RPC.
     */
    private RFView() {
    }

    public String getDataKey() {
        return dataKey;
    }

    public String getModelKey() {
        return modelKey;
    }

    public int getResponseVariable() {
        return responseVariable;
    }

    public int getNtree() {
        return ntree;
    }

    public int getMtry() {
        return mtry;
    }

    public String getConfusionKey() {
        return confusionKey;
    }

    public ConfusionMatrix getConfusionMatrix() {
        return confusionMatrix;
    }

    public TreeProperties getTrees() {
        return trees;
    }

    @Override
    public String toString() {
        return "RFView{" + "data_key=" + dataKey + ", model_key=" + modelKey
                + ", response_variable=" + responseVariable
                + ", ntree=" + ntree + ", mtry=" + mtry
                + ", confusion_key=" + confusionKey
                + ", confusion_matrix=" + confusionMatrix + ", trees=" + trees
                + super.toString() + '}';
    }

    public static class ConfusionMatrix implements IsSerializable {

        private String type = null;
        private float classificationError = 0;
        private int rowsSkipped = 0;
        private int rows = 0;
        private String[] header = null;
        private Integer[][] scores = null;
        private int usedTrees = 0;

        /**
         * No arg constructor needed for GWT-RPC.
         */
        private ConfusionMatrix() {
        }

        public String getType() {
            return type;
        }

        public float getClassificationError() {
            return classificationError;
        }

        public int getRowsSkipped() {
            return rowsSkipped;
        }

        public int getRows() {
            return rows;
        }

        public String[] getHeader() {
            return header;
        }

        public Integer[][] getScores() {
            return scores;
        }

        public int getUsedTrees() {
            return usedTrees;
        }

        @Override
        public String toString() {
            return "ConfusionMatrix{" + "type=" + type
                    + ", classification_erro=" + classificationError
                    + ", rows_skipped=" + rowsSkipped
                    + ", rows=" + rows
                    + ", header=" + Arrays.toString(header)
                    + ", scores=" + Arrays.deepToString(scores)
                    + ", used_trees=" + usedTrees + '}';
        }
    }

    public static class TreeProperties implements IsSerializable {

        private int numberBuilt = 0;
        private MinMeanMax depth = null;
        private MinMeanMax leaves = null;

        /**
         * No arg constructor needed for GWT-RPC.
         */
        private TreeProperties() {
        }

        public int getNumberBuilt() {
            return numberBuilt;
        }

        public MinMeanMax getDepth() {
            return depth;
        }

        public MinMeanMax getLeaves() {
            return leaves;
        }

        @Override
        public String toString() {
            return "TreeProperties{ number_built=" + numberBuilt
                    + ", depth=" + depth + ", leaves=" + leaves + "}";
        }

        public static class MinMeanMax implements IsSerializable {

            private double min = 0;
            private double mean = 0;
            private double max = 0;

            /**
             * No arg constructor needed for GWT-RPC.
             */
            private MinMeanMax() {
            }

            public double getMin() {
                return min;
            }

            public double getMean() {
                return mean;
            }

            public double getMax() {
                return max;
            }

            @Override
            public String toString() {
                return "MinMeanMax{" + "min=" + min + ", mean=" + mean
                        + ", max=" + max + '}';
            }
        }
    }
}
