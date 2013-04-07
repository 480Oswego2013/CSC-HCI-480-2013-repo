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
package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
import java.util.HashMap;

/**
 *
 */
public class RedirectRequestFactory {

    public static H2ORequest getRequest(final ResponseStatus status) {
        if (!status.isRedirect()) {
            throw new IllegalArgumentException("Not a redirect status: "
                    + status.getStatus());
        }
        String request = status.getRedirectRequest();
        HashMap<String, String> args = status.getRedirectRequestArgs();
        if (request.equals(ImportUrlBuilder.NAME)) {
            return new ImportUrlBuilder(args);
        } else if (request.equals(InspectBuilder.NAME)) {
            return new InspectBuilder(args);
        } else if (request.equals(ParseBuilder.NAME)) {
            return new ParseBuilder(args);
        } else if (request.equals(ProgressBuilder.NAME)) {
            return new ProgressBuilder(args);
        } else if (request.equals(RFBuilder.NAME)) {
            return new RFBuilder(args);
        } else if (request.equals(RFTreeViewBuilder.NAME)) {
            return new RFTreeViewBuilder(args);
        } else if (request.equals(RFViewBuilder.NAME)) {
            return new RFViewBuilder(args);
        } else if (request.equals(StoreViewBuilder.NAME)) {
            return new StoreViewBuilder(args);
        } else {
            throw new IllegalArgumentException("Unknown redirect: " + request);
        }
    }
}
