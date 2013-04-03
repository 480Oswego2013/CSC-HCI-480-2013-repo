package edu.oswego.csc480_hci521_2013.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import java.util.logging.Logger;

/**
 *
 */
public class TreeVisPlace extends Place {

    private static final Logger logger = Logger.getLogger(
            TreeVisPlace.class.getName());

    private String dataKey;
    private String modelKey;
    private int tree;

    public String getDataKey() {
        return dataKey;
    }

    public String getModelKey() {
        return modelKey;
    }

    public int getTree() {
        return tree;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public void setTree(int tree) {
        this.tree = tree;
    }

    @Prefix("TreeVis")
    public static class Tokenizer implements PlaceTokenizer<TreeVisPlace> {
        static final Logger logger = Logger.getLogger(TreeVisPlace.Tokenizer.class.getName());

        @Override
        public String getToken(TreeVisPlace place) {
            TokenParser tp = new TokenParser();
            tp.addArgument("datakey", place.getDataKey());
            tp.addArgument("modelkey", place.getModelKey());
            tp.addArgument("tree", Integer.toString(place.getTree()));
            String token = tp.serialize();
            logger.info("Generating token: " + token);
            return token;
        }

        @Override
        public TreeVisPlace getPlace(String token) {
            logger.info("Parsing token: " + token);
            TokenParser tp = new TokenParser(token);

            TreeVisPlace place = new TreeVisPlace();
            String dataKey = tp.getValue("datakey");
            String modelKey = tp.getValue("modelkey");
            String tree = tp.getValue("tree");
            if (dataKey != null) {
                place.setDataKey(dataKey);
            }
            if (modelKey != null) {
                place.setModelKey(modelKey);
            }
            if (tree != null) {
                place.setTree(Integer.parseInt(tree));
            }

            // TODO: handle invalid arguments

            return place;
        }
    }
}
