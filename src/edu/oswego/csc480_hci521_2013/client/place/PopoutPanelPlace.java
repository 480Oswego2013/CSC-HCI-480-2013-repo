package edu.oswego.csc480_hci521_2013.client.place;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import edu.oswego.csc480_hci521_2013.client.activity.mapper.PopoutPanelActivityMapper;

public class PopoutPanelPlace extends Place {
    
    static final Logger logger = Logger.getLogger(PopoutPanelPlace.class.getName());
    
    private PanelType paneltype;
    private String dataKey;
    private String modelKey;
    private int treeIndex;
    
    public PanelType getType() {
        return paneltype;
    }

    public void setType(PanelType type) {
        this.paneltype = type;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTreeIndex() {
	return treeIndex;
    }

    public void setTreeIndex(int treeIndex) {
	this.treeIndex = treeIndex;
    }

    private int index;
    
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
		for(PanelType type : PanelType.values())
			if(s.equalsIgnoreCase(type.toString()))
				return type;
		return null;
	}
};


@Prefix("Popout")
public static class Tokenizer implements PlaceTokenizer<PopoutPanelPlace> {

	@Override
	public String getToken(PopoutPanelPlace place) {
		TokenParser tp = new TokenParser();
		tp.addArgument("paneltype", place.getType().toString());
		tp.addArgument("modelkey", place.getModelKey());
		tp.addArgument("datakey", place.getDataKey());
		tp.addArgument("treeindex", String.valueOf(place.getTreeIndex()));
		return tp.serialize();
	}

	@Override
	public PopoutPanelPlace getPlace(String token) {
	    	logger.log(Level.INFO, "Parsing PopoutPanelPlace");
	    
		Map<String, String> map = TokenParser.parse(token);
		
		PanelType paneltype = PanelType.fromString(map.get("paneltype"));
		String modelKey = map.get("modelkey");
		String dataKey = map.get("datakey");
		int treeIndex = Integer.valueOf(map.get("treeindex"));
		
		PopoutPanelPlace popupPanelPlace = new PopoutPanelPlace();
		popupPanelPlace.setType(paneltype);
		popupPanelPlace.setModelKey(modelKey);
		popupPanelPlace.setDataKey(dataKey);
		popupPanelPlace.setTreeIndex(treeIndex);
		
		return popupPanelPlace;
	}

}

}
