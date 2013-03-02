package edu.oswego.csc480_hci521_2013.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface H2OServiceAsync
{
    void getParsedDataKeys(AsyncCallback<List<String>> callback);
    void getParsedData(String key, AsyncCallback<List<Map<String, String>>> callback);
}
