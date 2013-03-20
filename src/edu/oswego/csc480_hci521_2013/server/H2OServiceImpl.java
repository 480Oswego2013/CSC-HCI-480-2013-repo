package edu.oswego.csc480_hci521_2013.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.shared.h2o.RestException;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.Inspect;
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
import java.util.List;
import java.util.logging.Logger;

/**
 *
 */
//@SuppressWarnings("serial")
public class H2OServiceImpl extends RemoteServiceServlet implements H2OService {

    private static final Logger logger = Logger.getLogger(H2OServiceImpl.class.getName());
    private RestHandler rest = new RestHandler();

    public List<String> getParsedDataKeys() throws RestException {
        // FIXME: there is a default view of 20 rows on this...
        StoreView val = rest.get(new StoreViewBuilder()
                .setFilter(".hex").setView(1024), StoreView.class);
        List<String> keys = new ArrayList<String>();
        for (StoreView.Row row : val.getKeys()) {
            // NOTE: there is no way to know from this if it is parsed or not,
            //       but the naming convention is that parsed data ends in .hex
            //       we could possibly do an inspect call on each piece to
            //       check if it is parsed or not...
            if (row.getKey().endsWith(".hex")) {
                keys.add(row.getKey());
            }
        }
        return keys;
    }

    public StoreView getDataStores(StoreViewBuilder builder)
            throws RestException {
        return rest.get(builder, StoreView.class);
    }

    public Inspect getData(InspectBuilder builder) throws RestException {
        return rest.get(builder, Inspect.class);
    }

    public RFTreeView getTreeView(RFTreeViewBuilder builder)
            throws RestException {
        return rest.get(builder, RFTreeView.class);
    }

    public RF generateRandomForest(RFBuilder builder) throws RestException {
        return rest.get(builder, RF.class);
    }

    public RFView getRandomForestView(RFViewBuilder builder)
            throws RestException {
        return rest.get(builder, RFView.class);
    }
}
