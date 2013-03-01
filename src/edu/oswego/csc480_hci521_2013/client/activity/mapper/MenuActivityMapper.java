package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.activity.MenuActivity;
import edu.oswego.csc480_hci521_2013.client.place.MenuPlace;

public class MenuActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	private Activity currentActivity;

	public MenuActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {

		if(place instanceof MenuPlace)
			currentActivity = new MenuActivity((MenuPlace) place, clientFactory);
//			
//		else if(place instanceof Blah2Place)
//			return new Blah2Activity((Blah2Place) place, clientFactory);

		return currentActivity;
	}
}
