package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;

/**
 *
 */
public class StoreView implements IsSerializable
{
    private ArrayList<StoreViewRow> keys;
    private int num_keys;
    private String cloud_name;
    private String node_name;
    private ResponseStatus response;

    public ArrayList<StoreViewRow> getKeys()
    {
        return keys;
    }

    public int getNumKeys()
    {
        return num_keys;
    }

    public String getCloudName()
    {
        return cloud_name;
    }

    public String getNodeName()
    {
        return node_name;
    }

    public ResponseStatus getResponse()
    {
        return response;
    }

    @Override
    public String toString()
    {
        return "StoreView{" + "keys=" + keys + ", num_keys=" + num_keys + ", cloud_name=" + cloud_name + ", node_name=" + node_name + ", response=" + response + '}';
    }
}
