package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.activity.DataPanelActivity;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace.PanelType;

public class PopoutPanelActivityMapper implements ActivityMapper {

    static final Logger logger = Logger.getLogger(PopoutPanelActivityMapper.class.getName());
    
    private ClientFactory clientFactory;
	private Activity currentActivity;

	public PopoutPanelActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
	    
	    	logger.log(Level.INFO, "Mapping popout activity...");

		// PopoutPanelPlace
		if(place instanceof PopoutPanelPlace) {
		   	logger.log(Level.INFO, "   of type PopoutPanelPlace");
			PopoutPanelPlace ppp = (PopoutPanelPlace)place;
			PanelType type = ppp.getType();
			
			// Data panel
			if(type == PanelType.DATA) {
				if(currentActivity == null)
					currentActivity = new DataPanelActivity(ppp, clientFactory);
				else
					currentActivity = currentActivity.getClass() == DataPanelActivity.class ?
							currentActivity : new DataPanelActivity(ppp, clientFactory);
			
			// Vis panel
			} else if(type == PanelType.VIS) {
				if(currentActivity == null)
					currentActivity = new DataPanelActivity(ppp, clientFactory);
				else
					currentActivity = currentActivity.getClass() == DataPanelActivity.class ?
							currentActivity : new DataPanelActivity(ppp, clientFactory);
			}
			
		}

		return currentActivity;
	}
    
}
