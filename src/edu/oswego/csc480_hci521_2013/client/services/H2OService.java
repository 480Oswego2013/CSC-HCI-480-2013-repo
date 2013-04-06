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
