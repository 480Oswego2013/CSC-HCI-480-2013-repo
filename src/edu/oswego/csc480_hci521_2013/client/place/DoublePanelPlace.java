package edu.oswego.csc480_hci521_2013.client.place;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class DoublePanelPlace extends Place {
	
	static final Logger logger = Logger.getLogger(DoublePanelPlace.class.getName());

	public enum Location {
		EAST {
			public String toString() {
				return "east";
			}
		},
		WEST {
			public String toString() {
				return "west";
			}
		};

		public static Location fromString(String s) {
			for (Location loc : Location.values())
				if (s.equalsIgnoreCase(loc.toString()))
					return loc;
			return null;
		}
	};

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
			for (PanelType type : PanelType.values())
				if (s.equalsIgnoreCase(type.toString()))
					return type;
			return null;
		}
	};

	private static final PanelType defaultEast = PanelType.VIS;
	private static final PanelType defaultWest = PanelType.DATA;

	private PanelType east;
	private PanelType west;
	private String[] dataKeys;

	public DoublePanelPlace() {
		east = defaultEast;
		west = defaultWest;
		dataKeys = new String[0];
	}

	public DoublePanelPlace(PanelType east, PanelType west) {
		this.east = east;
		this.west = west;
		dataKeys = new String[0];
	}

	public PanelType getEast() {
		return east;
	}

	public PanelType getWest() {
		return west;
	}

	public String[] getDataKeys() {
		return dataKeys;
	}

	public void setDataKeys(String... dataKeys) {
		this.dataKeys = dataKeys;
	}
	
	public void addDataKey(String dataKey) {
		String[] newKeys = new String[dataKeys.length+1];
		System.arraycopy(dataKeys, 0, newKeys, 0, dataKeys.length);
		newKeys[newKeys.length-1] = dataKey;
		dataKeys = newKeys;
	}
	
	public void removeDataKey(int index) {
		String[] newDataKeys = new String[dataKeys.length-1];
		int i = 0;
		for(int j=0; j<newDataKeys.length; j++) {
			if(i == index)
				j--;
			else
				newDataKeys[j] = dataKeys[i];
			i++;
		}
		dataKeys = newDataKeys;
	}

	public void setEast(PanelType east) {
		this.east = east;
	}

	public void setWest(PanelType west) {
		this.west = west;
	}
	
	public DoublePanelPlace clone() {
		DoublePanelPlace place = new DoublePanelPlace(east, west);
		place.setDataKeys(dataKeys);
		return place;
	}

	// Tokenizer
	@Prefix("Main")
	public static class Tokenizer implements PlaceTokenizer<DoublePanelPlace> {

		@Override
		public String getToken(DoublePanelPlace place) {
			TokenParser tp = new TokenParser();
			tp.addArgument("east", place.getEast().toString());
			tp.addArgument("west", place.getWest().toString());
			if(place.getDataKeys().length > 0)
				tp.addArgument("datakey", place.getDataKeys());

			return tp.serialize();
		}

		@Override
		public DoublePanelPlace getPlace(String token) {
			TokenParser tp = new TokenParser(token);
			String east = tp.getValue(Location.EAST.toString());
			String west = tp.getValue(Location.WEST.toString());
			String[] keys = tp.getAllValues("datakey");

			DoublePanelPlace place = new DoublePanelPlace(PanelType.fromString(east),
					PanelType.fromString(west));
			place.setDataKeys(keys);
			return place;
		}

	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DoublePanelPlace))
			return false;
		DoublePanelPlace place = (DoublePanelPlace)obj;
		
		if(place.getEast() != getEast() || place.getWest() != getWest())
			return false;
		if(place.getDataKeys().length != getDataKeys().length)
			return false;
		for(int i=0; i<getDataKeys().length; i++)
			if(!place.getDataKeys()[i].equals(getDataKeys()[i]))
				return false;
		
		return true;
	}
	
}
