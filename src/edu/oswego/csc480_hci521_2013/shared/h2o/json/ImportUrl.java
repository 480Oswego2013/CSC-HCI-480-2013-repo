package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public class ImportUrl implements IsSerializable
{
    private String key;
    private String url;
    private ResponseStatus response;

    public String getKey()
    {
        return key;
    }

    public String getUrl()
    {
        return url;
    }

    public ResponseStatus getResponse()
    {
        return response;
    }

    @Override
    public String toString()
    {
        return "ImportUrl{" + "key=" + key + ", url=" + url + ", response=" + response + '}';
    }
}
