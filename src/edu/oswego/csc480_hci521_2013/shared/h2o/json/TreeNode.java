package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class TreeNode implements IsSerializable {

    private String field;
    private String condition;
    private float value;
    private String label;
    private TreeNode[] children;

    public TreeNode() {
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
        // NOTE: this is just for the current sigma call which needs the json...
        //       none of the json (de)serialization methods will work correctly in all of our situations.
        //       gson is the best with the h2o data, but only works on the server side.
        //       so this hack is to allow this to be converted back to json on the client side.
        //       if we convert more of the sigma javascript into an overlay then we can get rid of this.
        return "{"
            + "\"field\":" + (field == null ? field : "\"" + field + "\"") + ","
            + "\"condition\":" + (condition == null ? condition : "\"" + condition + "\"") + ","
            + "\"value\":" + value + ","
            + "\"label\":" + (label == null ? label : "\"" + label + "\"") + ","
            + "\"children\":"
            + (children == null
                ? children
                : "[" + children[0].toJson() + "," + children[1].toJson() + "]")
            + "}";
    }

    @Override
    public String toString() {
        return "TreeNode{" + "field=" + field + ", condition=" + condition
                + ", value=" + value + ", label=" + label
                + ", children=" + Arrays.toString(children) + '}';
    }
}
