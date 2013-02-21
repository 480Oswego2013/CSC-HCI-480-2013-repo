package edu.oswego.csc480_hci521_2013.h2owrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.StoreView;
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
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject response = parser.parse(json.toString()).getAsJsonObject();
        T sv = gson.fromJson(response, responseType);
        return sv;
    }
}
