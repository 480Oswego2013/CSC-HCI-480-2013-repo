package edu.oswego.csc480_hci521_2013.h2owrapper.json.objects;

import java.util.Map;

/**
 *
 */
public class ResponseStatus
{
    private String status;
    private String h2o;
    private String node;
    private int time;
    private String redirect_request;
    private Map<String, String> redirect_request_args;
    //"redirect_request_args" : {
    //  "destination_key" : "iris-parsed"
    //}

    public String getStatus()
    {
        return status;
    }

    public String getH2o()
    {
        return h2o;
    }

    public String getNode()
    {
        return node;
    }

    public int getTime()
    {
        return time;
    }

    public String getRedirectRequest()
    {
        return redirect_request;
    }

    public Map<String, String> getRedirectRequestArgs()
    {
        return redirect_request_args;
    }

    @Override
    public String toString()
    {
        return "ResponseStatus{" + "status=" + status + ", h2o=" + h2o + ", node=" + node + ", time=" + time + ", redirect_request=" + redirect_request + ", redirect_request_args=" + redirect_request_args + '}';
    }
}
