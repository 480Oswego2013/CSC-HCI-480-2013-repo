package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Map;

public class TreeProperties implements IsSerializable {

    private int number_built;
    private Map<String, Float> depth;
    private Map<String, Float> leaves;

    public int getNumberBuilt() {
        return number_built;
    }

    public float getDepthMin() {
        return depth.get("min");
    }

    public float getDepthMean() {
        return depth.get("mean");
    }

    public float getDepthMax() {
        return depth.get("max");
    }

    public float getLeavesMin() {
        return leaves.get("min");
    }

    public float getLeavesMean() {
        return leaves.get("mean");
    }

    public float getLeavesMax() {
        return leaves.get("max");
    }

    @Override
    public String toString() {
        return "TreeProperties{ number_built=" + number_built + ", depth=" + depth + ", leaves=" + leaves + "}";
    }
}
