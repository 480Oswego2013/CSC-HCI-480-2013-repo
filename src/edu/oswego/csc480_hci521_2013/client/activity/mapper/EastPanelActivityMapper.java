package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.activity.DataPanelActivity;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.PanelType;

public class EastPanelActivityMapper implements ActivityMapper {
	
	static final Logger logger = Logger.getLogger(PopoutPanelPlace.class.getName());

	private ClientFactory clientFactory;
	private Activity currentActivity;

	public EastPanelActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {

		// DoublePanelPlace
		if (place instanceof DoublePanelPlace) {
			DoublePanelPlace dpp = (DoublePanelPlace) place;
			PanelType type = dpp.getEast();
			logger.log(Level.INFO, "East type: "+type.toString());

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
