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

/**
 * Abstract base class for response objects.
 *
 * Responses can contain an error message if there was an error. Responses
 * generally contain a ResponseStatus object containing information about
 * the response.
 */
public abstract class AbstractResponse implements H2OResponse {

    /**
     * The error message.
     */
    private String error = null;
    /**
     * Information about the response.
     */
    private ResponseStatus response = null;

    @Override
    public String getError() {
        return error;
    }

    @Override
    public ResponseStatus getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return ", H2OResponse{" + "error=" + error
                + ", response=" + response + '}';
    }
}
