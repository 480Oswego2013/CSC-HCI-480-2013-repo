// Copyright 2013 State University of New York at Oswego
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package edu.oswego.csc480_hci521_2013.client.activity.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import edu.oswego.csc480_hci521_2013.client.activity.MenuActivity;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.popout.DataPanelPlace;
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
		if(place instanceof DataPanelPlace)
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
