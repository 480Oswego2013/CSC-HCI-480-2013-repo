package edu.oswego.csc480_hci521_2013.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DoublePanelPlace extends Place {

	public DoublePanelPlace() {
		
	}
	
	public static class Tokenizer implements PlaceTokenizer<DoublePanelPlace> {

		@Override
		public String getToken(DoublePanelPlace place) {
			return null;
		}

		@Override
		public DoublePanelPlace getPlace(String token) {
			return null;
		}

	}
}
