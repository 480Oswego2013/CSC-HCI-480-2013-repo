package edu.oswego.csc480_hci521_2013.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.oswego.csc480_hci521_2013.server.json.deserializers.InspectRowDeserializer;
import edu.oswego.csc480_hci521_2013.shared.h2o.H2OException;
import edu.oswego.csc480_hci521_2013.shared.h2o.RestException;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.H2OResponse;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.InspectRow;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.H2ORequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class RestHandler {

    static final Logger logger = Logger.getLogger(RestHandler.class.getName());
    private Gson gson;

    public RestHandler() {
        GsonBuilder gb = new GsonBuilder();
        // FIXME: can we get rid of the inspect row deserializer?
        gb.registerTypeAdapter(InspectRow.class, new InspectRowDeserializer());
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
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                json.append(line);
            }
            in.close();
            return json.toString();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
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
            logger.log(Level.SEVERE, sv.getError());
            throw new H2OException(sv.getError());
        }
        return sv;
    }

    public <T extends H2OResponse> T get(H2ORequest request, Class<T> responseType) throws RestException
    {
        String url = request.build();
        logger.log(Level.FINEST, url.toString());
        String json = fetch(url);
        logger.log(Level.FINEST, json);
        T val = parse(json, responseType);
        logger.log(Level.FINEST, val.toString());
        return val;
    }
}
