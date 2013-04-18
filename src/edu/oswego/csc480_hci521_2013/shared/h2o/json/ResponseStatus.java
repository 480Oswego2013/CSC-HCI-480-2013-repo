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
public class ResponseStatus implements IsSerializable {

    public static final String STATUS_POLL = "poll";
    public static final String STATUS_REDIRECT = "redirect";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_DONE = "done";
    private String status = null;
    private String h2o = null;
    private String node = null;
    private int time = 0;
    private String redirectRequest = null;
    private HashMap<String, String> redirectRequestArgs = null;
    private int progress = 0;
    private int progressTotal = 0;

    public ResponseStatus() {
    }

    public String getStatus() {
        return status;
    }

    public String getH2o() {
        return h2o;
    }

    public String getNode() {
        return node;
    }

    public int getTime() {
        return time;
    }

    public String getRedirectRequest() {
        return redirectRequest;
    }

    public HashMap<String, String> getRedirectRequestArgs() {
        return redirectRequestArgs;
    }

    public int getProgress() {
        return progress;
    }

    public int getProgressTotal() {
        return progressTotal;
    }

    public boolean isPoll() {
        return status.equals(STATUS_POLL);
    }

    public boolean isRedirect() {
        return status.equals(STATUS_REDIRECT);
    }

    public boolean isError() {
        return status.equals(STATUS_ERROR);
    }

    public boolean isDone() {
        return status.equals(STATUS_DONE);
    }
    
    // for testing reasons, setting the request   
    public void setRedirectRequest(String myRequest)
    {
    	this.redirectRequest = myRequest;
    }
    
    //setStatus method for testing 
    public void setStatus(String myStatus){
        this.status = myStatus;
    }
    
    
    @Override
    public String toString() {
        return "ResponseStatus{" + "status=" + status + ", h2o=" + h2o
                + ", node=" + node + ", time=" + time
                + ", redirect_request=" + redirectRequest
                + ", redirect_request_args=" + redirectRequestArgs
                + ", progress=" + progress
                + ", progress_total=" + progressTotal + '}';
    }
}
