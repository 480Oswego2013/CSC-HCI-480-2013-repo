package edu.oswego.csc480_hci521_2013.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.h2owrapper.RestHandler;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.ColumnDef;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.Inspect;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.InspectRow;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.StoreView;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.StoreViewRow;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.InspectBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.StoreViewBuilder;
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

    @Override
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

    @Override
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
}
