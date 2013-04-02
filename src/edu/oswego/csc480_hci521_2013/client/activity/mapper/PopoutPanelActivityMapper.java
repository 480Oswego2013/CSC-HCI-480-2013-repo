package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import edu.oswego.csc480_hci521_2013.client.activity.DataPanelActivity;
import edu.oswego.csc480_hci521_2013.client.activity.VisPanelActivity;
import edu.oswego.csc480_hci521_2013.client.place.popout.ConfusionMatrixPlace;
import edu.oswego.csc480_hci521_2013.client.place.popout.DataPanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.popout.PopoutPlace;
import edu.oswego.csc480_hci521_2013.client.place.popout.PopoutPlace.PopoutType;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;

public class PopoutPanelActivityMapper implements ActivityMapper {

    static final Logger logger = Logger.getLogger(PopoutPanelActivityMapper.class.getName());
    
    private Activity currentActivity;
    private EventBus eventBus;
    private H2OServiceAsync service;
    private DataPanelPresenter.View dataView;
    private PlaceController places;
    
    @Inject
	public PopoutPanelActivityMapper(EventBus eventBus, H2OServiceAsync service, DataPanelPresenter.View panelView) {
		this.eventBus = eventBus;
        this.service = service;
        this.dataView = panelView;
	}

    public void setPlaceController(PlaceController places) {
        this.places = places;
    }
    
	@Override
	public Activity getActivity(Place place) {
	    
	    	logger.log(Level.INFO, "Mapping popout activity..."+place.getClass().getName());

		// PopoutPlace
		if(place instanceof DataPanelPlace) {
		   	logger.log(Level.INFO, "   of type PopoutPanelPlace");
			PopoutPlace pp = (PopoutPlace)place;
			PopoutType type = pp.getType();
			
			// Data panel
			if(type == PopoutType.DATA) {
				DataPanelPlace dpp = (DataPanelPlace)pp;
				currentActivity = new DataPanelActivity(dpp, eventBus, service, dataView, places);
			
			// Confusion matrix
			} else if(type == PopoutType.CM) {
				ConfusionMatrixPlace cmp = (ConfusionMatrixPlace)pp;
				currentActivity = new VisPanelActivity(cmp, eventBus, service, places);
			}
			
		} else {
			currentActivity = null;
		}

		return currentActivity;
	}
    
}
