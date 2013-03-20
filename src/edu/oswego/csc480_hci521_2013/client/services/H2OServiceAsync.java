package edu.oswego.csc480_hci521_2013.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
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

/**
 *
 */
public interface H2OServiceAsync
{
    void getDataStores(StoreViewBuilder builder, AsyncCallback<StoreView> callback);
    void getData(InspectBuilder builder, AsyncCallback<Inspect> callback);
    void getTreeView(RFTreeViewBuilder builder, AsyncCallback<RFTreeView> callback);
    void generateRandomForest(RFBuilder builder, AsyncCallback<RF> callback);
    void getRandomForestView(RFViewBuilder builder, AsyncCallback<RFView> callback);
}
