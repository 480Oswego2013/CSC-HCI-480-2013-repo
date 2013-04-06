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
package edu.oswego.csc480_hci521_2013.client.place;

import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class DataTablePlace extends Place {

    private static final Logger logger = Logger.getLogger(
            DataTablePlace.class.getName());

    private String dataKey;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    @Prefix("DataTable")
    public static class Tokenizer implements PlaceTokenizer<DataTablePlace> {
        static final Logger logger = Logger.getLogger(Tokenizer.class.getName());

        @Override
        public String getToken(DataTablePlace place) {
            TokenParser tp = new TokenParser();
            tp.addArgument("datakey", place.getDataKey());
            String token = tp.serialize();
            logger.info("Generating token: " + token);
            return token;
        }

        @Override
        public DataTablePlace getPlace(String token) {
            logger.info("Parsing token: " + token);
            TokenParser tp = new TokenParser(token);

            DataTablePlace place = new DataTablePlace();
            String dataKey = tp.getValue("datakey");
            if (dataKey != null) {
                place.setDataKey(dataKey);
            }
            else {
                // TODO: handle invalid arguments
            }

            return place;
        }
    }
}
