package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.activity.DoublePanelActivity;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;

public class PanelActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	private Activity currentActivity;

	public PanelActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {

		if(place instanceof DoublePanelPlace)
			currentActivity = new DoublePanelActivity((DoublePanelPlace)place, clientFactory);
//			
//		else if(place instanceof Blah2Place)
//			return new Blah2Activity((Blah2Place) place, clientFactory);

		return currentActivity;
	}
}
