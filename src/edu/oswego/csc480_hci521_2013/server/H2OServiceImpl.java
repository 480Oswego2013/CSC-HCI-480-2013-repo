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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.shared.h2o.RestException;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ColumnDef;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.Inspect;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.InspectRow;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.StoreView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.InspectBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFTreeViewBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFViewBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.StoreViewBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
//@SuppressWarnings("serial")
public class H2OServiceImpl extends RemoteServiceServlet implements H2OService {

    static final Logger logger = Logger.getLogger(H2OServiceImpl.class.getName());
    RestHandler rest = new RestHandler();

    public List<String> getParsedDataKeys() throws Exception {
        try {
            // FIXME: there is a default view of 20 rows on this...
            String url = new StoreViewBuilder().setFilter(".hex").setView(1024).build();
            logger.log(Level.INFO, url.toString());
            String json = rest.fetch(url);
            logger.log(Level.INFO, json);
            StoreView val = rest.parse(json, StoreView.class);

            List<String> keys = new ArrayList<String>();
            for (StoreView.Row row : val.getKeys()) {
                // NOTE: there is no way to know from this if it is parsed or not,
                //       but the naming convention is that parsed data ends in .hex.
                //       we could possibly do an inspect call on each piece to check if it is parsed or not...
                if (row.getKey().endsWith(".hex")) {
                    keys.add(row.getKey());
                }
            }
            return keys;
        } catch (RestException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public List<Map<String, String>> getParsedData(String key) throws Exception {
        try {
            String url = new InspectBuilder(key).build();
            logger.log(Level.INFO, url.toString());
            String json = rest.fetch(url);
            logger.log(Level.INFO, json);
            Inspect val = rest.parse(json, Inspect.class);
            logger.log(Level.INFO, val.toString());

            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            for (InspectRow row : val.getRows()) {
                Map<String, String> rowMap = new HashMap<String, String>();
                data.add(rowMap);
                for (ColumnDef column : val.getCols()) {
                    rowMap.put(column.getName(), row.getData(column.getName()).toString());
                }
            }
            return data;
        } catch (RestException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public ArrayList<String> getColumnHeaders(String dataKey) throws Exception{
        try {
            String url = new InspectBuilder(dataKey).build();
            logger.log(Level.INFO, url.toString());
            String json = rest.fetch(url);
            logger.log(Level.INFO, json);
            Inspect val = rest.parse(json, Inspect.class);
            logger.log(Level.INFO, val.toString());

            ArrayList<String> columnHeaders = new ArrayList<String>();

            //Iterate through Arraylist of ColumnDef
            for(ColumnDef column : val.getCols()){
                columnHeaders.add(column.getName());
            }

            return columnHeaders;
        } catch (RestException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public String getTreeAsJson(String dataKey, String modelKey, int index) throws Exception {
        try {
            String url = new RFTreeViewBuilder(dataKey, modelKey).setTreeNumber(index).build();
            logger.log(Level.INFO, url.toString());
            String json = rest.fetch(url);
            logger.log(Level.INFO, json);

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject response = parser.parse(json.toString()).getAsJsonObject();
            if (response.has("error")) {
                throw new RestException(response.get("error").getAsString());
            }
            return gson.toJson(response.get("tree"));
        } catch (RestException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public RFTreeView getTreeView(String dataKey, String modelKey, int index) throws Exception {
        try {
            String url = new RFTreeViewBuilder(dataKey, modelKey).setTreeNumber(index).build();
            logger.log(Level.INFO, url.toString());
            String json = rest.fetch(url);
            logger.log(Level.INFO, json);
            RFTreeView val = rest.parse(json, RFTreeView.class);
            logger.log(Level.INFO, val.toString());
            return val;
        } catch (RestException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public RF generateRandomForest(RFBuilder builder) throws Exception {
        try {
            String url = builder.build();
            logger.log(Level.INFO, "H2OService: generateRandomForest urL: " + url.toString());
            String json = rest.fetch(url);
            logger.log(Level.INFO, "H2OService: generateRandomForest json: " + json);
            RF val = rest.parse(json, RF.class);
            logger.log(Level.INFO, val.toString());
            return val;
        } catch (RestException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public RFView getRandomForestView(RFViewBuilder builder) throws Exception {
        try {
            String url = builder.build();
            logger.log(Level.INFO, url.toString());
            String json = rest.fetch(url);
            logger.log(Level.INFO, json);
            RFView val = rest.parse(json, RFView.class);
            logger.log(Level.INFO, val.toString());
            return val;
        } catch (RestException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }
}
