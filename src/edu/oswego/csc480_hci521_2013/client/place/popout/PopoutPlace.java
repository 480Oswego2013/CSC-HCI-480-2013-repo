package edu.oswego.csc480_hci521_2013.client.place.popout;

import com.google.gwt.place.shared.Place;

public abstract class PopoutPlace extends Place {
	
	public enum PopoutType {
		DATA {
			public String toString() {
				return "data";
			}
		},
		CM {
			public String toString() {
				return "cm";
			}
		};

		public static PopoutType fromString(String s) {
			if(s == null) return null;
			for (PopoutType type : PopoutType.values())
				if (s.equalsIgnoreCase(type.toString()))
					return type;
			return null;
		}
	};
	
	private PopoutType type;
	
	public PopoutPlace(PopoutType type) {
		this.type = type;
	}
	
	public PopoutType getType() {
		return type;
	}

}
