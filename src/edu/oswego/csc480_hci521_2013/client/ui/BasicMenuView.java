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

package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.inject.Inject;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicMenuView extends Composite implements MenuView {

	private Presenter presenter;
    static final Logger logger = Logger.getLogger(BasicMenuView.class.getName());
    private final H2OServiceAsync service;

    @Inject
	public BasicMenuView(H2OServiceAsync service) {
        this.service = service;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void buildGui() {
		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.setWidth("100%");
		mainPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);

		MenuBar mainMenu = new MenuBar(false);
        mainMenu.addItem(getParsedDataMenu());

		mainPanel.add(mainMenu);
		initWidget(mainPanel);
	}

    private MenuItem getParsedDataMenu()
    {
        final MenuBar menu = new MenuBar(true);
        MenuItem item = new MenuItem("Parsed Data Sets", false, menu);
        service.getParsedDataKeys(new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.SEVERE, caught.toString());
            }

            @Override
            public void onSuccess(List<String> result)
            {
                for (String key: result) {
                    menu.addItem(new MenuItem(key, false, presenter.getMenuCommand(key)));
                }
            }
        });
        return item;
    }
}
