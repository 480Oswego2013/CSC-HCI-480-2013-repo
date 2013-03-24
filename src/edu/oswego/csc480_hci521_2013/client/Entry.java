/*
 * Copyright 2013 State University of New York at Oswego
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import edu.oswego.csc480_hci521_2013.client.activity.mapper.MenuActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.PanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.MenuPlace;

public class Entry implements EntryPoint {

	private Place defaultPlace = new MenuPlace();
	private SimplePanel menuPanel = new SimplePanel();
	private SimplePanel panelPanel = new SimplePanel();

	@Override
	public void onModuleLoad() {

		// Create ClientFactory using deferred binding (just because I say so)
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		// Start ActivityManagers for the main panel and content panel
		ActivityMapper mainActivityMapper = new MenuActivityMapper(clientFactory);
		ActivityManager mainActivityManager = new ActivityManager(mainActivityMapper, eventBus);
		mainActivityManager.setDisplay(menuPanel);

		ActivityMapper panelActivityMapper = new PanelActivityMapper(clientFactory);
		ActivityManager panelActivityManager = new ActivityManager(panelActivityMapper, eventBus);
		panelActivityManager.setDisplay(panelPanel);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		// Add to RootPanel and handle history token, open double panel place
		RootPanel.get().add(menuPanel);
		RootPanel.get().add(panelPanel);
		historyHandler.handleCurrentHistory();
		placeController.goTo(new DoublePanelPlace());

	}
}
