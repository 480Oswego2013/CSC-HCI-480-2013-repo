/*
 * Copyright 2013 State University of New York at Oswego
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.HashMap;

/**
 *
 */
public class ResponseStatus implements IsSerializable
{
    public static final String STATUS_POLL = "poll";
    public static final String STATUS_REDIRECT = "redirect";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_DONE = "done";

    private String status;
    private String h2o;
    private String node;
    private int time;
    private String redirect_request;
    private HashMap<String, String> redirect_request_args;
    private int progress;
    private int progress_total;

    private ResponseStatus() {
    }

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

    public HashMap<String, String> getRedirectRequestArgs()
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
        return status.equals(STATUS_POLL);
    }

    public boolean isRedirect()
    {
        return status.equals(STATUS_REDIRECT);
    }

    public boolean isError()
    {
        return status.equals(STATUS_ERROR);
    }

    public boolean isDone()
    {
        return status.equals(STATUS_DONE);
    }
    
    // for testing reasons, setting the request   
    public void setRedirectRequest(String myRequest)
    {
    	this.redirect_request = myRequest;
    }
    
    // this method lets me set the arguments for the hash map
    public void setRedirectRequestArgs(HashMap<String, String> myMap)
    {
    	this.redirect_request_args = myMap;
    }
    
    @Override
    public String toString()
    {
        return "ResponseStatus{" + "status=" + status + ", h2o=" + h2o + ", node=" + node + ", time=" + time + ", redirect_request=" + redirect_request + ", redirect_request_args=" + redirect_request_args + ", progress=" + progress + ", progress_total=" + progress_total + '}';
    }
}
