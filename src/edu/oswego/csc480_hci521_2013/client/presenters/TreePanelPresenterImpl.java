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
package edu.oswego.csc480_hci521_2013.client.presenters;

import edu.oswego.csc480_hci521_2013.client.ui.TreePanelView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;

/**
 *
 */
public class TreePanelPresenterImpl implements TreePanelPresenter, TabPanelPresenter {

    TreePanelView view;
    private RFTreeView data;
    private String datakey;
    private String modelkey;
    private int treeIndex;

    public TreePanelPresenterImpl(TreePanelView view, RFTreeView data, String datakey, String modelkey, int treeIndex) {
        this.view = view;
        this.data = data;
        this.datakey = datakey;
        this.modelkey = modelkey;
        this.treeIndex = treeIndex;
    }

    public RFTreeView getData() {
        return data;
    }

    public String getDatakey() {
        return datakey;
    }

    public String getModelkey() {
        return modelkey;
    }

    public int getTreeIndex() {
        return treeIndex;
    }

    @Override
    public void added() {
    }

    @Override
    public void removed() {
    }

    @Override
    public TreePanelView getView() {
        return view;
    }

    @Override
    public void setView(TreePanelView view) {
        this.view = view;
    }
}
