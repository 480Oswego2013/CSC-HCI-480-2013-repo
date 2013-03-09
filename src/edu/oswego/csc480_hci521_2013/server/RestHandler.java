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
