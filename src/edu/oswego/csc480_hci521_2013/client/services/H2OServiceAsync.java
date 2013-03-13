package edu.oswego.csc480_hci521_2013.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface H2OServiceAsync
{
    void getColumnHeaders(String dataKey, AsyncCallback<ArrayList<String>> callback);
    void getParsedDataKeys(AsyncCallback<List<String>> callback);
    void getParsedData(String key, AsyncCallback<List<Map<String, String>>> callback);
    void getTreeAsJson(String dataKey, String modelKey, int index, AsyncCallback<String> callback);

    void getTreeView(String dataKey, String modelKey, int index, AsyncCallback<RFTreeView> callback);
    void generateRandomForest(RFBuilder builder, AsyncCallback<RF> callback);
    void getRandomForestView(String dataKey, String modelKey, AsyncCallback<RFView> callback);
}
