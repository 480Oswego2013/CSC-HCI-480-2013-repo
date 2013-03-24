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

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;

import edu.oswego.csc480_hci521_2013.client.ui.MenuView;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;

public interface ClientFactory {

    EventBus getEventBus();

    PlaceController getPlaceController();

    MenuView getMainView();

    PanelView getDoublePanelView();

    H2OServiceAsync getH2OService();

    DataPanelPresenter.View getDataPanelPresenterView();

    ConfusionMatrixPresenter.View getConfusionMatrixPresenterView();
}
