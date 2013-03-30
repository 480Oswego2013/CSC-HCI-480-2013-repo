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

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.BasicMenuView;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelView;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;

public class ClientFactoryImpl implements ClientFactory {

	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
    private static final H2OServiceAsync h2oService = GWT.create(H2OService.class);

	private MenuView menuView;
	private DataPanelPresenter.View dataPanelView;
	private DoublePanelView doublePanelView;
	
	

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public MenuView getMainView() {
        if (menuView == null) {
            menuView = new BasicMenuView();
        }
		return menuView;
	}

	@Override
	public PanelView getDoublePanelView() {
        if (doublePanelView == null) {
            doublePanelView = new DoublePanelView();
        }
		return doublePanelView;
	}
	
	@Override
	public DataPanelPresenter.View getDataPanelView() {
        if (dataPanelView == null) {
            dataPanelView = new DataPanelViewImpl();
        }
		return dataPanelView;
	}

    @Override
    public H2OServiceAsync getH2OService() {
        return h2oService;
    }

}
