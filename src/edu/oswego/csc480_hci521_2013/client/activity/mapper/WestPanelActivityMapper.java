package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import edu.oswego.csc480_hci521_2013.client.activity.DataPanelActivity;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.PanelType;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter.View;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;

public class WestPanelActivityMapper implements ActivityMapper {

	private Activity currentActivity;
    private EventBus eventBus;
    private H2OServiceAsync service;
    private View panelView;
    private PlaceController places;
    
    @Inject
	public WestPanelActivityMapper(EventBus eventBus, H2OServiceAsync service, View panelView) {
		this.eventBus = eventBus;
        this.service = service;
        this.panelView = panelView;
	}

    public void setPlaceController(PlaceController places) {
        this.places = places;
    }
    
	@Override
	public Activity getActivity(Place place) {

		// DoublePanelPlace
		if (place instanceof DoublePanelPlace) {
			DoublePanelPlace dpp = (DoublePanelPlace) place;
			PanelType type = dpp.getWest();

			// Data panel
			if (type == PanelType.DATA) {
                //EventBus eventBus, H2OServiceAsync service, View panelView, PlaceController places
				currentActivity = new DataPanelActivity(Location.EAST, dpp, eventBus, service, panelView, places);
//				if (currentActivity == null)
//					currentActivity = new DataPanelActivity(Location.WEST, dpp,
//							clientFactory);
//				else
//					currentActivity = currentActivity.getClass() == DataPanelActivity.class ? currentActivity
//							: new DataPanelActivity(Location.WEST, dpp,
//									clientFactory);

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
