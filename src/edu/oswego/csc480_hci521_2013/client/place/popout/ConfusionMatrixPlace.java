// Copyright 2013 State University of New York at Oswego
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package edu.oswego.csc480_hci521_2013.client.place.popout;

import java.util.logging.Logger;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import edu.oswego.csc480_hci521_2013.client.place.TokenParser;

public class ConfusionMatrixPlace extends PopoutPlace {

	static final Logger logger = Logger.getLogger(ConfusionMatrixPlace.class
			.getName());

	private String dataKey;
	private int treeCount;
	private int[] ignoreCols;
	
	public ConfusionMatrixPlace() {
		super(PopoutType.CM);
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public int getTreeCount() {
		return treeCount;
	}

	public void setTreeCount(int treeCount) {
		this.treeCount = treeCount;
	}

	public int[] getIgnoreCols() {
		return ignoreCols;
	}

	public void setIgnoreCols(int[] ignoreCols) {
		this.ignoreCols = ignoreCols;
	}
	
	public void setIgnoreCols(String[] ignoreCols) {
		int[] arr = new int[ignoreCols.length];
		for(int i=0; i<ignoreCols.length; i++)
			arr[i] = Integer.parseInt(ignoreCols[i]);
		this.ignoreCols = arr;
	}

	@Prefix("CM")
	public static class Tokenizer implements PlaceTokenizer<ConfusionMatrixPlace> {

		@Override
		public String getToken(ConfusionMatrixPlace place) {
			TokenParser tp = new TokenParser();
			tp.addArgument("paneltype", place.getType().toString());
			tp.addArgument("datakey", place.getDataKey());
			tp.addArgument("treecount", String.valueOf(place.getTreeCount()));
			String[] arr = new String[place.getIgnoreCols().length];
			for(int i=0; i<place.getIgnoreCols().length; i++)
				arr[i] = String.valueOf(place.getIgnoreCols()[i]);
			tp.addArgument("ignorecols", arr);
			return tp.serialize();
		}

		@Override
		public ConfusionMatrixPlace getPlace(String token) {
			TokenParser tp = new TokenParser(token);

			String dataKey = tp.getValue("datakey");
			int treeCount = tp.getValue("treecount") != null ? Integer.parseInt(tp.getValue("treecount")) : -1;
			String[] ignoreCols = tp.getAllValues("ignorecols") != null ? tp.getAllValues("ignorecols") : new String[]{};

			ConfusionMatrixPlace place = new ConfusionMatrixPlace();
			place.setDataKey(dataKey);
			place.setTreeCount(treeCount);
			place.setIgnoreCols(ignoreCols);

			return place;
		}

	}

}
