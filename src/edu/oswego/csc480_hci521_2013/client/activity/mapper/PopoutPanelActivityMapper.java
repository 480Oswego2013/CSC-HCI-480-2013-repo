package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.activity.DataPanelActivity;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace.PanelType;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;

public class PopoutPanelActivityMapper implements ActivityMapper {

    static final Logger logger = Logger.getLogger(PopoutPanelActivityMapper.class.getName());
    
    private Activity currentActivity;
    private EventBus eventBus;
    private H2OServiceAsync service;
    private DataPanelPresenter.View panelView;
    private PlaceController places;
    
    @Inject
	public PopoutPanelActivityMapper(EventBus eventBus, H2OServiceAsync service, DataPanelPresenter.View panelView) {
		this.eventBus = eventBus;
        this.service = service;
        this.panelView = panelView;
	}

    public void setPlaceController(PlaceController places) {
        this.places = places;
    }
    
	@Override
	public Activity getActivity(Place place) {
	    
	    	logger.log(Level.INFO, "Mapping popout activity..."+place.getClass().getName());

		// PopoutPanelPlace
		if(place instanceof PopoutPanelPlace) {
		   	logger.log(Level.INFO, "   of type PopoutPanelPlace");
			PopoutPanelPlace ppp = (PopoutPanelPlace)place;
			PanelType type = ppp.getType();
			
			// Data panel
			if(type == PanelType.DATA) {
				currentActivity = new DataPanelActivity(ppp, eventBus, service, panelView, places);
//				if(currentActivity == null)
//					currentActivity = new DataPanelActivity(ppp, clientFactory);
//				else
//					currentActivity = currentActivity.getClass() == DataPanelActivity.class ?
//							currentActivity : new DataPanelActivity(ppp, clientFactory);
			
			// Vis panel
			} else if(type == PanelType.VIS) {
				if(currentActivity == null)
					currentActivity = new DataPanelActivity(ppp, eventBus, service, panelView, places);
				else
					currentActivity = currentActivity.getClass() == DataPanelActivity.class ?
							currentActivity : new DataPanelActivity(ppp, eventBus, service, panelView, places);
			}
			
		} else {
			currentActivity = null;
		}

		return currentActivity;
	}
    
}
