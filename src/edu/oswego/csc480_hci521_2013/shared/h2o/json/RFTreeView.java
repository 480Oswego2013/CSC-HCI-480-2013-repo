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
        return "RFTreeView{" + "depth=" + depth + ", leaves=" + leaves + ", tree=" + tree + '}';
    }
}
