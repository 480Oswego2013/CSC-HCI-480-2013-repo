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
package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import edu.oswego.csc480_hci521_2013.client.activity.mapper.EastPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.MenuActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.PopoutPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.WestPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;

public class Entry implements EntryPoint {

	private final AppGinjector injector = GWT.create(AppGinjector.class);
    private static AppPlaceHistoryMapper historyMapper;
	private static final Place defaultPlace = new DoublePanelPlace();
	
	private EventBus eventBus;
	private PlaceController placeController;
	private SimplePanel northPanel, eastPanel, westPanel, popoutPanel;

	@Override
	public void onModuleLoad() {
		init();
		
		// Create ActivityManager for north panel (menu)
		MenuActivityMapper northActivityMapper = injector.getMenuActivityMapper();
        northActivityMapper.setPlaceController(placeController);
		ActivityManager northActivityManager = new ActivityManager(northActivityMapper, eventBus);
		northActivityManager.setDisplay(northPanel);
		
		// Create ActivityManager for west panel (data)
		WestPanelActivityMapper westActivityMapper = injector.getWestPanelActivityMapper();
        westActivityMapper.setPlaceController(placeController);
		ActivityManager westActivityManager = new ActivityManager(westActivityMapper, eventBus);
		westActivityManager.setDisplay(westPanel);
		
		// Create ActivityManager for east panel (visualization)
		EastPanelActivityMapper eastActivityMapper = injector.getEastPanelActivityMapper();
        eastActivityMapper.setPlaceController(placeController);
		ActivityManager eastActivityManager = new ActivityManager(eastActivityMapper, eventBus);
		eastActivityManager.setDisplay(eastPanel);
		
		// Create ActivityManager for popout panel (only visible with specific place)
		PopoutPanelActivityMapper popoutActivityMapper = injector.getPopoutPanelActivityMapper();
        popoutActivityMapper.setPlaceController(placeController);
		ActivityManager popoutActivityManager = new ActivityManager(popoutActivityMapper, eventBus);
		popoutActivityManager.setDisplay(popoutPanel);
		

		historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		buildGui();
		historyHandler.handleCurrentHistory();
//		placeController.goTo(defaultPlace);

	}
	
	public static AppPlaceHistoryMapper getPlaceHistoryMapper() {
		return historyMapper;
	}
	
	private void init() {
		// Create ClientFactory using deferred binding
		
		eventBus = this.injector.getEventBus();
		placeController = new PlaceController(eventBus);
		
		northPanel = new SimplePanel();
		eastPanel = new SimplePanel();
		westPanel = new SimplePanel();
		popoutPanel = new SimplePanel();
	}
	
	private  void buildGui() {
		HorizontalPanel contents = new HorizontalPanel();
		contents.add(popoutPanel);
		contents.add(westPanel);
		contents.add(eastPanel);
		contents.setCellWidth(westPanel, "50%");
		contents.setCellWidth(eastPanel, "50%");
		contents.setWidth("100%");
		
		HeaderPanel container = new HeaderPanel();
		container.setHeaderWidget(northPanel);
		container.setContentWidget(contents);;
		
		RootLayoutPanel.get().add(container);
	}
	
	
}
