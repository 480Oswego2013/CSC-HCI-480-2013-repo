package edu.oswego.csc480_hci521_2013.client.place;

import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PopoutDataPanelPlace extends Place {

    static final Logger logger = Logger.getLogger(PopoutDataPanelPlace.class
            .getName());

    private String dataKey;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    @Prefix("PopoutData")
    public static class Tokenizer implements PlaceTokenizer<PopoutDataPanelPlace> {
        static final Logger logger = Logger.getLogger(Tokenizer.class.getName());

        @Override
        public String getToken(PopoutDataPanelPlace place) {
            TokenParser tp = new TokenParser();
            tp.addArgument("datakey", place.getDataKey());
            String token = tp.serialize();
            logger.info("Generating token: " + token);
            return token;
        }

        @Override
        public PopoutDataPanelPlace getPlace(String token) {
            logger.info("Parsing token: " + token);
            TokenParser tp = new TokenParser(token);

            PopoutDataPanelPlace popupPanelPlace = new PopoutDataPanelPlace();
            String dataKey = tp.getValue("datakey");
            if (dataKey != null) {
                popupPanelPlace.setDataKey(dataKey);
            }

            return popupPanelPlace;
        }
    }
}
