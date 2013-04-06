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

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import java.util.logging.Logger;

/**
 *
 */
public class ConfusionMatrixPlace extends Place {

    private static final Logger logger = Logger.getLogger(
            ConfusionMatrixPlace.class.getName());

    private RF randomForest;

    public RF getRandomForest() {
        return randomForest;
    }

    public void setRandomForest(RF randomForest) {
        this.randomForest = randomForest;
    }

    @Prefix("ConfusionMatrix")
    public static class Tokenizer implements PlaceTokenizer<ConfusionMatrixPlace> {
        static final Logger logger = Logger.getLogger(ConfusionMatrixPlace.Tokenizer.class.getName());

        @Override
        public String getToken(ConfusionMatrixPlace place) {
            TokenParser tp = new TokenParser();
            tp.addArgument("datakey", place.getRandomForest().getDataKey());
            tp.addArgument("modelkey", place.getRandomForest().getModelKey());
            tp.addArgument("ntree", Integer.toString(
                    place.getRandomForest().getNtree()));
            tp.addArgument("responseVariable", Integer.toString(
                    place.getRandomForest().getResponseVariable()));
            tp.addArgument("oobError", Boolean.toString(
                    place.getRandomForest().isOutOfBagErrorEstimate()));
            String token = tp.serialize();

            logger.info("Generating token: " + token);
            return token;
        }

        @Override
        public ConfusionMatrixPlace getPlace(String token) {
            logger.info("Parsing token: " + token);
            TokenParser tp = new TokenParser(token);
            ConfusionMatrixPlace place = new ConfusionMatrixPlace();

            String dataKey = tp.getValue("datakey");
            String modelKey = tp.getValue("modelkey");
            String ntree = tp.getValue("ntree");
            String responseVariable = tp.getValue("responseVariable");
            String oobError = tp.getValue("oobError");
            if (dataKey != null
                    && modelKey != null
                    && ntree != null
                    && responseVariable != null
                    && oobError != null)
            {
                place.setRandomForest(new RF(
                        dataKey,
                        modelKey,
                        Integer.parseInt(ntree),
                        Integer.parseInt(responseVariable),
                        Boolean.parseBoolean(oobError)));
            }
            else {
                // TODO: handle invalid arguments
            }

            return place;
        }
    }
}
