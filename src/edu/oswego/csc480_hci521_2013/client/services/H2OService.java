package edu.oswego.csc480_hci521_2013.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
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
import java.util.List;

/**
 *
 */
@RemoteServiceRelativePath("h2oService")
public interface H2OService extends RemoteService
{
    List<String> getParsedDataKeys() throws Exception;

    StoreView getDataStores(StoreViewBuilder builder) throws Exception;
    Inspect getData(InspectBuilder builder) throws Exception;
    RFTreeView getTreeView(RFTreeViewBuilder builder) throws Exception;
    RF generateRandomForest(RFBuilder builder) throws Exception;
    RFView getRandomForestView(RFViewBuilder builder) throws Exception;
}
