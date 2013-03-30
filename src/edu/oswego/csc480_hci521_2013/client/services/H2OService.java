package edu.oswego.csc480_hci521_2013.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFViewBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RemoteServiceRelativePath("h2oService")
public interface H2OService extends RemoteService
{
    ArrayList<String> getColumnHeaders(String dataKey) throws Exception;
    List<String> getParsedDataKeys() throws Exception;
    List<Map<String, String>> getParsedData(String key) throws Exception;
    String getTreeAsJson(String dataKey, String modelKey, int index) throws Exception;

    RFTreeView getTreeView(String dataKey, String modelKey, int index) throws Exception;
    RF generateRandomForest(RFBuilder builder) throws Exception;
    RFView getRandomForestView(RFViewBuilder builder) throws Exception;
}
