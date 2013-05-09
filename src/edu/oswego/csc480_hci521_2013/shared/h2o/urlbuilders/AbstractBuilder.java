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
package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 */
abstract class AbstractBuilder implements H2ORequest {

    private static final String PROTOCOL = "http";
    private static final String HOST = "localhost";
    private static final int PORT = 54321;
    private String protocol = PROTOCOL;
    private String host = HOST;
    private int port = PORT;
    private String page;
    private HashMap<String, ArrayList<String>> args = new HashMap<String, ArrayList<String>>();

    protected AbstractBuilder() {
    }

    protected AbstractBuilder(String page) {
        this.page = "/" + page + ".json";
    }

    protected AbstractBuilder setArgs(HashMap<String, String> args) {
        for (Entry<String, String> item : args.entrySet()) {
            addMultiArg(item.getKey(), item.getValue());
        }
        return this;
    }

    protected AbstractBuilder addArg(String key, String value) {
        args.put(key, new ArrayList<String>());
        args.get(key).add(value);
        return this;
    }

    protected AbstractBuilder addMultiArg(String key, String value) {
        if (!args.containsKey(key)) {
            args.put(key, new ArrayList<String>());
        }
        args.get(key).add(value);
        return this;
    }

    public AbstractBuilder setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public AbstractBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public AbstractBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public HashMap<String, ArrayList<String>> getArgs(){
        return args;
    }

    @Override
    public String build(UrlEncoder encoder) {
        StringBuilder query = new StringBuilder();
        for (String key : args.keySet()) {
            for (String value : args.get(key)) {
                if (query.length() == 0) {
                    query.append(key).append('=').append(encoder.encode(value));
                } else {
                    query.append('&').append(key).append('=').append(encoder.encode(value));
                }
            }
        }
        String url = protocol + "://" + host + ":" + port + page
                + ((query.length() > 0) ? "?" : "") + query.toString();
        return url;
    }
}
