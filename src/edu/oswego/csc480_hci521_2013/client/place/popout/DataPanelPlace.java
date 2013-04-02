package edu.oswego.csc480_hci521_2013.client.place.popout;

import java.util.logging.Logger;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import edu.oswego.csc480_hci521_2013.client.place.TokenParser;

public class DataPanelPlace extends PopoutPlace {

	static final Logger logger = Logger.getLogger(DataPanelPlace.class
			.getName());

	private String dataKey;
	private String modelKey;
	private int treeIndex;
	
	public DataPanelPlace() {
		super(PopoutType.DATA);
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

	@Prefix("Data")
	public static class Tokenizer implements PlaceTokenizer<DataPanelPlace> {

		@Override
		public String getToken(DataPanelPlace place) {
			TokenParser tp = new TokenParser();
			tp.addArgument("paneltype", place.getType().toString());
			tp.addArgument("modelkey", place.getModelKey());
			tp.addArgument("datakey", place.getDataKey());
			tp.addArgument("treeindex", String.valueOf(place.getTreeIndex()));
			return tp.serialize();
		}

		@Override
		public DataPanelPlace getPlace(String token) {
			TokenParser tp = new TokenParser(token);

			String modelKey = tp.getValue("modelkey");
			String dataKey = tp.getValue("datakey");
			int treeIndex = tp.getValue("treeindex") != null ? Integer.parseInt(tp.getValue("treeindex")) : -1;

			DataPanelPlace place = new DataPanelPlace();
			place.setModelKey(modelKey);
			place.setDataKey(dataKey);
			place.setTreeIndex(treeIndex);

			return place;
		}

	}

}
