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

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
abstract class AbstractBuilder implements IsSerializable
{
    private static final String PROTOCOL = "http";
    private static final String HOST = "localhost";
    private static final int PORT = 54321;

    private String protocol = PROTOCOL;
    private String host = HOST;
    private int port = PORT;
    private String page;

    private Map<String, String> args = new HashMap<String, String>();
    private List<Arg> multiargs = new ArrayList<Arg>();

    protected AbstractBuilder()
    {
    }

    protected AbstractBuilder(String page)
    {
        this.page = "/" + page;
    }

    protected AbstractBuilder addArg(String key, String value)
    {
        args.put(key, value);
        return this;
    }

    protected AbstractBuilder addMultiArg(String key, String value)
    {
        multiargs.add(new Arg(key, value));
        return this;
    }

    public AbstractBuilder setProtocol(String protocol)
    {
        this.protocol = protocol;
        return this;
    }

    public AbstractBuilder setHost(String host)
    {
        this.host = host;
        return this;
    }

    public AbstractBuilder setPort(int port)
    {
        this.port = port;
        return this;
    }

    public String build()
    {
        String query = null;
        for (String key: args.keySet()) {
            String value = args.get(key);
            if (query == null) {
                query = key + "=" + value;
            }
            else {
                query += "&" + key + "=" + value;
            }
        }
        for (Arg arg: multiargs) {
            String key = arg.key;
            String value = arg.value;
            if (query == null) {
                query = key + "=" + value;
            }
            else {
                query += "&" + key + "=" + value;
            }
        }
        String url = protocol + "://" + host + ":" + port + page;
        if (query != null) {
            url += "?" + query;
        }
        return url;
    }

}
