package edu.oswego.csc480_hci521_2013.client.place;

import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PopoutPanelPlace extends Place {

    static final Logger logger = Logger.getLogger(PopoutPanelPlace.class
            .getName());
    private PanelType type;
    private String dataKey;
    private String modelKey;
    private int treeIndex;

    public PanelType getType() {
        return type;
    }

    public void setType(PanelType type) {
        this.type = type;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public int getTreeIndex() {
        return treeIndex;
    }

    public void setTreeIndex(int treeIndex) {
        this.treeIndex = treeIndex;
    }

    public enum PanelType {

        DATA {
            public String toString() {
                return "data";
            }
        },
        VIS {
            public String toString() {
                return "vis";
            }
        };

        public static PanelType fromString(String s) {
            if (s == null) {
                return null;
            }
            for (PanelType type : PanelType.values()) {
                if (s.equalsIgnoreCase(type.toString())) {
                    return type;
                }
            }
            return null;
        }
    };

    @Prefix("Popout")
    public static class Tokenizer implements PlaceTokenizer<PopoutPanelPlace> {
        static final Logger logger = Logger.getLogger(Tokenizer.class.getName());

        @Override
        public String getToken(PopoutPanelPlace place) {
            TokenParser tp = new TokenParser();
            tp.addArgument("paneltype", place.getType().toString());
            tp.addArgument("modelkey", place.getModelKey());
            tp.addArgument("datakey", place.getDataKey());
            tp.addArgument("treeindex", String.valueOf(place.getTreeIndex()));
            String token = tp.serialize();
            logger.info("Generating token: " + token);
            return token;
        }

        @Override
        public PopoutPanelPlace getPlace(String token) {
            logger.info("Parsing token: " + token);
            TokenParser tp = new TokenParser(token);

            PopoutPanelPlace popupPanelPlace = new PopoutPanelPlace();
            if (tp.getValue("paneltype") != null) {
                PanelType paneltype = PanelType.fromString(
                        tp.getValue("paneltype"));
                popupPanelPlace.setType(paneltype);
            }
            String modelKey = tp.getValue("modelkey");
            if (modelKey != null) {
                popupPanelPlace.setModelKey(modelKey);
            }
            String dataKey = tp.getValue("datakey");
            if (dataKey != null) {
                popupPanelPlace.setDataKey(dataKey);
            }
            if (tp.getValue("treeindex") != null) {
                int treeIndex = Integer.parseInt(tp.getValue("treeindex"));
                popupPanelPlace.setTreeIndex(treeIndex);
            }

            return popupPanelPlace;
        }
    }
}
