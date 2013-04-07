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
package edu.oswego.csc480_hci521_2013.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.oswego.csc480_hci521_2013.server.json.deserializers
        .InspectRowDeserializer;
import edu.oswego.csc480_hci521_2013.shared.h2o.H2OException;
import edu.oswego.csc480_hci521_2013.shared.h2o.RestException;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.H2OResponse;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.Inspect;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.H2ORequest;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.UrlEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class RestHandler {

    private static final Logger LOGGER = Logger.getLogger(
            RestHandler.class.getName());
    private Gson gson;
    private UrlEncoder encoder = new ServerUrlEncoder();

    public RestHandler() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Inspect.Row.class, new InspectRowDeserializer());
        gson = gb.create();
    }

    /**
     * Fetch the json string
     *
     * @param query the url
     * @return json string
     * @throws Exception
     */
    public String fetch(String query) throws RestException {
        try {
            URL url = new URL(query);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                json.append(line);
            }
            in.close();
            return json.toString();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RestException(e);
        }
    }

    /**
     * parse the json string into a response object
     *
     * @param <T> the type of the response object
     * @param json the json string
     * @param responseType
     * @return
     * @throws RestException
     */
    public <T extends H2OResponse> T parse(String json, Class<T> responseType) throws H2OException
    {
        JsonParser parser = new JsonParser();
        JsonObject response = parser.parse(json).getAsJsonObject();
        T sv = gson.fromJson(response, responseType);
        if (sv.getResponse().isError()) {
            LOGGER.log(Level.SEVERE, sv.getError());
            throw new H2OException(sv.getError());
        }
        return sv;
    }

    public <T extends H2OResponse> T get(H2ORequest request, Class<T> responseType) throws RestException
    {
        String url = request.build(encoder);
        LOGGER.log(Level.INFO, url.toString());
        String json = fetch(url);
        LOGGER.log(Level.INFO, json);
        T val = parse(json, responseType);
        LOGGER.log(Level.INFO, val.toString());
        return val;
    }
}
