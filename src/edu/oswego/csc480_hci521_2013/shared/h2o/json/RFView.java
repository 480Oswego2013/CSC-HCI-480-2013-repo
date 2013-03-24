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

/**
 *
 */
public class RFView extends H2OResponse {

    private String data_key;
    private String model_key;
    private int response_variable;
    private int ntree;
    private int mtry;
    private String confusion_key;
    private ConfusionMatrix confusion_matrix;
    private TreeProperties trees;

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
            + ", response_variable=" + response_variable + ", ntree=" + ntree
            + ", mtry=" + mtry + ", confusion_key=" + confusion_key
            + ", confusion_matrix=" + confusion_matrix + ", trees=" + trees
            + " " + super.toString() + '}';
    }

    public static class TreeProperties implements IsSerializable {

        private int number_built;
        private MinMeanMax depth;
        private MinMeanMax leaves;

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
            return "TreeProperties{ number_built=" + number_built + ", depth=" + depth + ", leaves=" + leaves + "}";
        }

        public static class MinMeanMax implements IsSerializable {
            private double min;
            private double mean;
            private double max;

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
                return "MinMeanMax{" + "min=" + min + ", mean=" + mean + ", max=" + max + '}';
            }
        }
    }
}
