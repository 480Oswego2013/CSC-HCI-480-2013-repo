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
public class RFTreeView extends AbstractResponse {

    private int depth;
    private int leaves;
    private TreeNode tree;

    private RFTreeView() {
    }

    public int getDepth() {
        return depth;
    }

    public int getLeaves() {
        return leaves;
    }

    public TreeNode getTree() {
        return tree;
    }

    @Override
    public String toString() {
        return "RFTreeView{" + "depth=" + depth + ", leaves=" + leaves
                + ", tree=" + tree + super.toString() + '}';
    }

    public static class TreeNode implements IsSerializable {

        private String field;
        private String condition;
        private float value;
        private String label;
        private TreeNode[] children;

        private TreeNode() {
        }

        public String getField() {
            return field;
        }

        public String getCondition() {
            return condition;
        }

        public float getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        public TreeNode[] getChildren() {
            return children;
        }

        public String toJson() {
            // NOTE: this is just for the current sigma call which needs
            //       the json... none of the json (de)serialization methods
            //       will work correctly in all of our situations. gson is
            //       the best with the h2o data, but only works on the server
            //       side. so this hack is to allow this to be converted back
            //       to json on the client side. if we convert more of the
            //       sigma javascript into an overlay then we can get rid
            //       of this.
            return "{"
                    + "\"field\":"
                    + (field == null ? field : "\"" + field + "\"") + ","
                    + "\"condition\":"
                    + (condition == null
                    ? condition : "\"" + condition + "\"") + ","
                    + "\"value\":" + value + ","
                    + "\"label\":"
                    + (label == null ? label : "\"" + label + "\"") + ","
                    + "\"children\":"
                    + (children == null
                    ? children
                    : "[" + children[0].toJson() + ","
                    + children[1].toJson() + "]")
                    + "}";
        }

        @Override
        public String toString() {
            return "TreeNode{" + "field=" + field + ", condition=" + condition
                    + ", value=" + value + ", label=" + label
                    + ", children=" + Arrays.toString(children) + '}';
        }
    }
}
