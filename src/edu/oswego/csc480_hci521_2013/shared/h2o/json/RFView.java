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
public class RFView extends AbstractResponse {

    private String data_key;
    private String model_key;
    private int response_variable;
    private int ntree;
    private int mtry;
    private String confusion_key;
    private ConfusionMatrix confusion_matrix;
    private TreeProperties trees;

    private RFView() {
    }

    public String getDataKey() {
        return data_key;
    }

    public String getModelKey() {
        return model_key;
    }

    public int getResponseVariable() {
        return response_variable;
    }

    public int getNtree() {
        return ntree;
    }

    public int getMtry() {
        return mtry;
    }

    public String getConfusionKey() {
        return confusion_key;
    }

    public ConfusionMatrix getConfusionMatrix() {
        return confusion_matrix;
    }

    public TreeProperties getTrees() {
        return trees;
    }

    @Override
    public String toString() {
        return "RFView{" + "data_key=" + data_key + ", model_key=" + model_key
                + ", response_variable=" + response_variable
                + ", ntree=" + ntree + ", mtry=" + mtry
                + ", confusion_key=" + confusion_key
                + ", confusion_matrix=" + confusion_matrix + ", trees=" + trees
                + super.toString() + '}';
    }

    public static class ConfusionMatrix implements IsSerializable {

        private String type;
        private float classification_error;
        private int rows_skipped;
        private int rows;
        private String[] header;
        private Integer[][] scores;
        private int used_trees;

        private ConfusionMatrix() {
        }

        public String getType() {
            return type;
        }

        public float getClassificationError() {
            return classification_error;
        }

        public int getRowsSkipped() {
            return rows_skipped;
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
            return used_trees;
        }

        @Override
        public String toString() {
            return "ConfusionMatrix{" + "type=" + type
                    + ", classification_erro=" + classification_error
                    + ", rows_skipped=" + rows_skipped
                    + ", rows=" + rows
                    + ", header=" + Arrays.toString(header)
                    + ", scores=" + Arrays.deepToString(scores)
                    + ", used_trees=" + used_trees + '}';
        }
    }

    public static class TreeProperties implements IsSerializable {

        private int number_built;
        private MinMeanMax depth;
        private MinMeanMax leaves;

        private TreeProperties() {
        }

        public int getNumberBuilt() {
            return number_built;
        }

        public MinMeanMax getDepth() {
            return depth;
        }

        public MinMeanMax getLeaves() {
            return leaves;
        }

        @Override
        public String toString() {
            return "TreeProperties{ number_built=" + number_built
                    + ", depth=" + depth + ", leaves=" + leaves + "}";
        }

        public static class MinMeanMax implements IsSerializable {

            private double min;
            private double mean;
            private double max;

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
