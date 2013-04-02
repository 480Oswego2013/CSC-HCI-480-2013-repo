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
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.PanelType;
import edu.oswego.csc480_hci521_2013.client.place.popout.DataPanelPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.VisPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;

public class EastPanelActivityMapper implements ActivityMapper {

	static final Logger logger = Logger.getLogger(DataPanelPlace.class
			.getName());

	private Activity currentActivity;
	private EventBus eventBus;
	private H2OServiceAsync service;
	private DataPanelPresenter.View dataView;
	private VisPanelPresenter.View visView;
	private PlaceController placeController;

	@Inject
	public EastPanelActivityMapper(EventBus eventBus, H2OServiceAsync service,
			DataPanelPresenter.View dataView, VisPanelPresenter.View visView) {
		this.eventBus = eventBus;
		this.service = service;
		this.dataView = dataView;
		this.visView = visView;
	}

	public void setPlaceController(PlaceController places) {
		this.placeController = places;
	}

	@Override
	public Activity getActivity(Place place) {

		// DoublePanelPlace
		if (place instanceof DoublePanelPlace) {
			DoublePanelPlace dpp = (DoublePanelPlace) place;
			PanelType type = dpp.getEast();

			// Data panel
			if (type == PanelType.DATA)
				currentActivity = new DataPanelActivity(Location.EAST, dpp,
						eventBus, service, dataView, placeController);

			// Vis panel
			else if (type == PanelType.VIS)
				currentActivity = new VisPanelActivity(Location.EAST, dpp,
						eventBus, service, visView, placeController);

		} else if (place instanceof DataPanelPlace) {
			currentActivity = null;
		}

		return currentActivity;
	}
}
