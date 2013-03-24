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

/**
 *
 */
public class RFTreeView extends H2OResponse {

    private int depth;
    private int leaves;
    private TreeNode tree;

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
        return "RFTreeView{" + "depth=" + depth + ", leaves=" + leaves + ", tree=" + tree + " " + super.toString() + '}';
    }
}
