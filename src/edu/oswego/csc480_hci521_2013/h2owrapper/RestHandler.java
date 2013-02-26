package edu.oswego.csc480_hci521_2013.h2owrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.deserializers.InspectRowDeserializer;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.InspectRow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 */
public class RestHandler
{
    public String fetch(URL query) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(query.openStream()));
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            json.append(line);
        }
        return json.toString();
    }

    public <T> T parse(String json, Class<T> responseType) throws Exception
    {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(InspectRow.class, new InspectRowDeserializer());
        Gson gson = gb.create();
        JsonParser parser = new JsonParser();
        JsonObject response = parser.parse(json.toString()).getAsJsonObject();
        if (response.has("error")) {
            throw new RestException(response.get("error").getAsString());
        }
        T sv = gson.fromJson(response, responseType);
        return sv;
    }
}
