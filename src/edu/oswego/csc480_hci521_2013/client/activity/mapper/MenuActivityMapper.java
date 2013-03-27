package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.activity.MenuActivity;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;

public class MenuActivityMapper implements ActivityMapper {

	private Activity currentActivity;
    private PlaceController places;
    private MenuView menuView;

    @Inject
	public MenuActivityMapper(MenuView menuView) {
		this.menuView = menuView;
	}

    public void setPlaceController(PlaceController places) {
        this.places = places;
    }
    
	@Override
	public Activity getActivity(Place place) {
		
		// PopoutPanelPlace
		if(place instanceof PopoutPanelPlace)
			currentActivity = null;
		
		// DoublePanelPlace
		else if(place instanceof DoublePanelPlace) {
			DoublePanelPlace dpp = (DoublePanelPlace)place;
			currentActivity = new MenuActivity(dpp, places, menuView);
//			if(currentActivity == null)
//				currentActivity = new MenuActivity(dpp, clientFactory);
//			else
//				currentActivity = currentActivity.getClass() == MenuActivity.class ?
//					currentActivity : new MenuActivity(dpp, clientFactory);
		}

		return currentActivity;
	}
}
