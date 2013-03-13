package edu.oswego.csc480_hci521_2013.client.place;

import java.util.Map;
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
	private String dataKey;

	public DoublePanelPlace() {
		east = defaultEast;
		west = defaultWest;
	}

	public DoublePanelPlace(PanelType east, PanelType west) {
		this.east = east;
		this.west = west;
	}

	public PanelType getEast() {
		return east;
	}

	public PanelType getWest() {
		return west;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public void setEast(PanelType east) {
		this.east = east;
	}

	public void setWest(PanelType west) {
		this.west = west;
	}

	// Tokenizer
	@Prefix("Main")
	public static class Tokenizer implements PlaceTokenizer<DoublePanelPlace> {

		@Override
		public String getToken(DoublePanelPlace place) {
			TokenParser tp = new TokenParser();
			tp.addArgument("east", place.getEast().toString());
			tp.addArgument("west", place.getWest().toString());

			return tp.serialize();
		}

		@Override
		public DoublePanelPlace getPlace(String token) {
			Map<String, String> map = TokenParser.parse(token);

			String east = map.get(Location.EAST.toString());
			String west = map.get(Location.WEST.toString());
			
			logger.log(Level.INFO, "\neast:"+east+" west:"+west+"\n");

			return new DoublePanelPlace(PanelType.fromString(east),
					PanelType.fromString(west));
		}

	}
}
