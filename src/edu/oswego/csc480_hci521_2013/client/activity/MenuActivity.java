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
package edu.oswego.csc480_hci521_2013.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.events.InspectDataEvent;
import edu.oswego.csc480_hci521_2013.client.place.MenuPlace;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;

public class MenuActivity extends AbstractActivity implements MenuView.Presenter {

	private ClientFactory clientFactory;

	public MenuActivity(MenuPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		MenuView menuView = clientFactory.getMainView();
		menuView.setPresenter(this);
		menuView.buildGui();

		containerWidget.setWidget(menuView.asWidget());
	}

	@Override
	public String mayStop() {
//		return "Please don't leave me.";
		return null;
	}


	// Presenter methods

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public Command getMenuCommand(final String value) {
		return new Command() {
			@Override
			public void execute() {
                clientFactory.getEventBus().fireEvent(new InspectDataEvent(value));
			}
		};
	}
}
