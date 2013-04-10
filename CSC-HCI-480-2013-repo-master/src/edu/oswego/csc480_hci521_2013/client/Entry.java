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
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import edu.oswego.csc480_hci521_2013.client.activity.AppActivityMapper;

import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;

public class Entry implements EntryPoint {

    private final AppGinjector injector = GWT.create(AppGinjector.class);
    private static AppPlaceHistoryMapper historyMapper;

    private Place defaultPlace = new DoublePanelPlace();

    private SimpleLayoutPanel container = new SimpleLayoutPanel();

    @Override
    public void onModuleLoad() {
        EventBus eventBus = injector.getEventBus();
        PlaceController placeController = injector.getPlaceController();

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new AppActivityMapper(
                injector.getDoublePanelView(),
                placeController,
                injector.getH2OServiceAsync());
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(container);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        //AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        RootLayoutPanel.get().add(container);
        historyHandler.handleCurrentHistory();

    }

    public static AppPlaceHistoryMapper getPlaceHistoryMapper() {
        return historyMapper;
    }
}
