package edu.oswego.csc480_hci521_2013.h2owrapper.json.objects;

/**
 *
 */
public class RFTreeView
{
    private int depth;
    private int leaves;
    private TreeNode tree;
    private ResponseStatus response;

    public int getDepth()
    {
        return depth;
    }

    public int getLeaves()
    {
        return leaves;
    }

    public TreeNode getTree()
    {
        return tree;
    }

    public ResponseStatus getResponse()
    {
        return response;
    }

    @Override
    public String toString()
    {
        return "RFTreeView{" + "depth=" + depth + ", leaves=" + leaves + ", tree=" + tree + ", response=" + response + '}';
    }
}
