package edu.oswego.csc480_hci521_2013.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MenuPlace extends Place {
	
	private String contentId;

	public MenuPlace() {
		
	}
	
	
	public static class Tokenizer implements PlaceTokenizer<MenuPlace> {

		@Override
		public String getToken(MenuPlace place) {
			return null;
		}

		@Override
		public MenuPlace getPlace(String token) {
			return null;
		}

	}
}
