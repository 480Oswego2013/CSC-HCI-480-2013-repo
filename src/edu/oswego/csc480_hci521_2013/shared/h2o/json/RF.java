package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public class RF implements IsSerializable
{
    private String data_key;
    private String model_key;
    private int ntree;
    private int response_variable;
    private boolean out_of_bag_error_estimate;
    private ResponseStatus response;

    public String getDataKey()
    {
        return data_key;
    }

    public String getModelKey()
    {
        return model_key;
    }

    public int getNtree()
    {
        return ntree;
    }

    public int getResponseVariable()
    {
        return response_variable;
    }

    public boolean isOutOfBagErrorEstimate()
    {
        return out_of_bag_error_estimate;
    }

    public ResponseStatus getResponse()
    {
        return response;
    }

    @Override
    public String toString()
    {
        return "RF{" + "data_key=" + data_key + ", model_key=" + model_key + ", ntree=" + ntree + ", response_variable=" + response_variable + ", out_of_bag_error_estimate=" + out_of_bag_error_estimate + ", response=" + response + '}';
    }
}
