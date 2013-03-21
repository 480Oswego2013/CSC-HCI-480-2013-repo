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
package edu.oswego.csc480_hci521_2013.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;


import edu.oswego.csc480_hci521_2013.client.events.InspectDataEvent;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuActivity extends AbstractActivity implements MenuView.Presenter {

    private static final Logger logger = Logger.getLogger(DoublePanelActivity.class.getName());
    private MenuView view;
    private PlaceController places;
    private EventBus eventBus;

    @Inject
    public MenuActivity(MenuView view, PlaceController places, EventBus eventBus) {
        this.view = view;
        this.places = places;
        this.eventBus = eventBus;
        logger.log(Level.INFO, "MenuActivity bus: " + eventBus.hashCode());
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        this.view.setPresenter(this);
        this.view.buildGui();

        containerWidget.setWidget(this.view.asWidget());
        logger.log(Level.INFO, "MenuActivity started");
    }

    @Override
    public String mayStop() {
//		return "Please don't leave me.";
        return null;
    }

    // Presenter methods
    @Override
    public void goTo(Place place) {
        this.places.goTo(place);
    }

    @Override
    public Command getMenuCommand(final String value) {
        return new Command() {
            @Override
            public void execute() {
                eventBus.fireEvent(new InspectDataEvent(value));
                logger.log(Level.INFO, "Selected dataset: " + value);
            }
        };
    }
}
