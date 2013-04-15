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
 * Represents the H2O RFTreeView json response.
 * @see edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFTreeViewBuilder
 */
public class RFTreeView extends AbstractResponse {

    /**
     * the max depth of this tree.
     */
    private int depth = 0;
    /**
     * the number of leaves in this tree.
     */
    private int leaves = 0;
    /**
     * the root node of the tree.
     */
    private TreeNode tree = null;

    /**
     * No arg constructor needed for GWT-RPC.
     */
    private RFTreeView() {
    }

    /**
     *
     * @return the max depth of this tree
     */
    public int getDepth() {
        return depth;
    }

    /**
     *
     * @return the number of leaves in this tree
     */
    public int getLeaves() {
        return leaves;
    }

    /**
     *
     * @return the root node of this tree
     */
    public TreeNode getTree() {
        return tree;
    }

    @Override
    public String toString() {
        return "RFTreeView{" + "depth=" + depth + ", leaves=" + leaves
                + ", tree=" + tree + super.toString() + '}';
    }

    /**
     * a node in the tree.
     */
    public static class TreeNode implements IsSerializable {

        /**
         * the name of the field tested in this node.
         */
        private String field = null;
        /**
         * the condition of the test.
         */
        private String condition = null;
        /**
         * the value threshold tested.
         */
        private float value = 0;
        /**
         * the label of the leaf node.
         */
        private String label = null;
        /**
         * children nodes of this node. 0 is left child, 1 is right child.
         */
        private TreeNode[] children = null;

        /**
         * No arg constructor needed for GWT-RPC.
         */
        private TreeNode() {
        }

        /**
         *
         * @return the name of the field tested in this node.
         */
        public String getField() {
            return field;
        }

        /**
         *
         * @return the condition of the test.
         */
        public String getCondition() {
            return condition;
        }

        /**
         *
         * @return the value threshold tested.
         */
        public float getValue() {
            return value;
        }

        /**
         *
         * @return the label of the leaf node.
         */
        public String getLabel() {
            return label;
        }

        /**
         *
         * @return children nodes of this node. 0 is left child, 1 is right
         *         child.
         */
        public TreeNode[] getChildren() {
            return children;
        }

        /**
         * This is a bit of a hack but it is needed because GWT AutoBean
         * proxies do not work well with the json we are given from H2O.
         * So we just manually do the json conversion here. This is used
         * for rendering the tree in javascript. Eventually this could be
         * removed if we convert our tree code from javascript to a
         * GWT JSNI wrapper and use this object directly.
         *
         * @return The json representation of this node
         */
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
