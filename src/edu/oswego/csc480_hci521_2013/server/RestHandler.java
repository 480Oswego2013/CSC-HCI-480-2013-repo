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
import edu.oswego.csc480_hci521_2013.h2owrapper.json.deserializers.InspectRowDeserializer;
import edu.oswego.csc480_hci521_2013.shared.h2o.RestException;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.H2OResponse;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.InspectRow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 */
public class RestHandler {

    Gson gson;

    public RestHandler() {
        GsonBuilder gb = new GsonBuilder();
        // FIXME: can we get rid of the inspect row deserializer?
        gb.registerTypeAdapter(InspectRow.class, new InspectRowDeserializer());
        gson = gb.create();
    }

    public String fetch(String query) throws Exception {
        URL url = new URL(query);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            json.append(line);
        }
        return json.toString();
    }

    public <T extends H2OResponse> T parse(String json, Class<T> responseType) throws Exception
    {
        JsonParser parser = new JsonParser();
        JsonObject response = parser.parse(json).getAsJsonObject();
        T sv = gson.fromJson(response, responseType);
        if (sv.getResponse().isError()) {
            throw new RestException(sv.getError());
        }
        return sv;
    }
}
