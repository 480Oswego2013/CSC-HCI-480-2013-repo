package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.activity.DataPanelActivity;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.PanelType;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;

public class WestPanelActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	private Activity currentActivity;

	public WestPanelActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {

		// DoublePanelPlace
		if (place instanceof DoublePanelPlace) {
			DoublePanelPlace dpp = (DoublePanelPlace) place;
			PanelType type = dpp.getWest();

			// Data panel
			if (type == PanelType.DATA) {
				if (currentActivity == null)
					currentActivity = new DataPanelActivity(Location.EAST, dpp,
							clientFactory);
				else
					currentActivity = currentActivity.getClass() == DataPanelActivity.class ? currentActivity
							: new DataPanelActivity(Location.EAST, dpp,
									clientFactory);

				// Vis panel
			} else if (type == PanelType.VIS) {
				currentActivity = null;
			}

		} else if (place instanceof PopoutPanelPlace) {
			currentActivity = null;
		}

		return currentActivity;
	}
}
