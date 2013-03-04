package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Map;

/**
 *
 */
public class ResponseStatus implements IsSerializable
{
    private String status;
    private String h2o;
    private String node;
    private int time;
    private String redirect_request;
    private Map<String, String> redirect_request_args;
    private int progress;
    private int progress_total;

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

    public int getProgress()
    {
        return progress;
    }

    public int getProgressTotal()
    {
        return progress_total;
    }

    public boolean isPoll()
    {
        return status.equals("poll");
    }

    public boolean isRedirect()
    {
        return status.equals("redirect");
    }

    @Override
    public String toString()
    {
        return "ResponseStatus{" + "status=" + status + ", h2o=" + h2o + ", node=" + node + ", time=" + time + ", redirect_request=" + redirect_request + ", redirect_request_args=" + redirect_request_args + ", progress=" + progress + ", progress_total=" + progress_total + '}';
    }
}
