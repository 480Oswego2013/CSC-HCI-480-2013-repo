package edu.oswego.csc480_hci521_2013.server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.h2owrapper.RestException;
import edu.oswego.csc480_hci521_2013.h2owrapper.RestHandler;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.ColumnDef;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.Inspect;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.InspectRow;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.StoreView;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.StoreViewRow;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.InspectBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.RFTreeViewBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.StoreViewBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.TreeNode;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@SuppressWarnings("serial")
public class H2OServiceImpl extends RemoteServiceServlet implements H2OService
{
    RestHandler rest = new RestHandler();

    public List<String> getParsedDataKeys() throws Exception
    {
        try {
            URL url = new StoreViewBuilder().build();
            String json = rest.fetch(url);
            StoreView val = rest.parse(json, StoreView.class);

            List<String> keys = new ArrayList<>();
            for (StoreViewRow row: val.getKeys()) {
                // NOTE: there is no way to know for sure if it is parsed data but the .hex naming convention is what h2o tends to use
                if (row.getKey().endsWith(".hex")) {
                    keys.add(row.getKey());
                }
            }
            return keys;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Map<String, String>> getParsedData(String key) throws Exception
    {
        try {
            URL url = new InspectBuilder(key).build();
            String json = rest.fetch(url);
            Inspect val = rest.parse(json, Inspect.class);

            List<Map<String, String>> data = new ArrayList<>();
            for (InspectRow row: val.getRows()) {
                Map<String, String> rowMap = new HashMap<>();
                data.add(rowMap);
                for (ColumnDef column: val.getCols()) {
                    rowMap.put(column.getName(), row.getData(column.getName()));
                }
            }
            return data;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String getTreeAsJson(String dataKey, String modelKey, int index) throws Exception
    {
        try {
            URL url = new RFTreeViewBuilder(dataKey, modelKey).setTreeNumber(index).build();
            String json = rest.fetch(url);
            RFTreeView val = rest.parse(json, RFTreeView.class);

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject response = parser.parse(json.toString()).getAsJsonObject();
            if (response.has("error")) {
                throw new RestException(response.get("error").getAsString());
            }
            return gson.toJson(response.get("tree"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public TreeNode getTree(String dataKey, String modelKey, int index) throws Exception
    {
        try {
            URL url = new RFTreeViewBuilder(dataKey, modelKey).setTreeNumber(index).build();
            String json = rest.fetch(url);
            RFTreeView val = rest.parse(json, RFTreeView.class);
            return val.getTree();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
