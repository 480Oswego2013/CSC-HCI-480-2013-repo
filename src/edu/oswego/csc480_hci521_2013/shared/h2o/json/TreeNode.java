package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.List;

/**
 *
 */
public class TreeNode implements IsSerializable
{
    private String field;
    private String condition;
    private float value;
    private String label;
    private List<TreeNode> children;

    public TreeNode() {}

    public String getField()
    {
        return field;
    }

    public String getCondition()
    {
        return condition;
    }

    public float getValue()
    {
        return value;
    }

    public String getLabel()
    {
        return label;
    }

    public List<TreeNode> getChildren()
    {
        return children;
    }

    @Override
    public String toString()
    {
        return "TreeNode{" + "field=" + field + ", condition=" + condition + ", value=" + value + ", label=" + label + ", children=" + children + '}';
    }
}
