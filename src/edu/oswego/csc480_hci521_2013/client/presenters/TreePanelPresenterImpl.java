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
