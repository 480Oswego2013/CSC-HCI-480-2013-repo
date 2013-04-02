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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.MenuItem;
import edu.oswego.csc480_hci521_2013.client.AppPlaceHistoryMapper;
import edu.oswego.csc480_hci521_2013.client.Entry;
import edu.oswego.csc480_hci521_2013.client.events.PopoutDataPanelEvent;
import edu.oswego.csc480_hci521_2013.client.events.PopoutDataPanelEventHandler;

import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEventHandler;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.PopoutDataPanelPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.presenters.DoublePanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelView;
import edu.oswego.csc480_hci521_2013.client.ui.TreePanel;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoublePanelActivity extends AbstractActivity implements DoublePanelPresenter {

    static final Logger logger = Logger.getLogger(DoublePanelActivity.class.getName());
	private DoublePanelView view;
    private PlaceController places;
    private EventBus eventBus;
    private H2OServiceAsync service;

    private List<DataPanelPresenter> westTabs = new ArrayList<DataPanelPresenter>();

	public DoublePanelActivity(DoublePanelView view, PlaceController places, EventBus eventBus, H2OServiceAsync service) {
        // FIXME: this constructor should go away...
        this(null, view, places, service);
	}

	public DoublePanelActivity(DoublePanelPlace place, DoublePanelView view, PlaceController places, H2OServiceAsync service) {
        // TODO: handle the place, it will carry args for what we should display.
		this.view = view;
        this.places = places;
        this.service = service;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        this.eventBus = eventBus;
		containerWidget.setWidget(view);
        bind(eventBus);
        loadParsedDataMenu();
        logger.log(Level.INFO, "DoublePanelActivity started");
	}

    private void bind(EventBus eventBus) {
        eventBus.addHandler(TreeVisEvent.TYPE, new TreeVisEventHandler() {
            @Override
            public void onViewData(TreeVisEvent e) {
                addVisTab(e.getData().getDataKey(), e.getData().getModelKey(), e.getIndex());
            }
        });
        eventBus.addHandler(RFGenerateEvent.TYPE, new RFGenerateEventHandler() {
            @Override
            public void onStart(RFGenerateEvent e) {
                logger.log(Level.INFO, "Adding confusion matrix...");
                addConfusionMatrixTab(e.getData());
            }
        });
        eventBus.addHandler(PopoutDataPanelEvent.TYPE, new PopoutDataPanelEventHandler() {
            @Override
            public void onPopout(PopoutDataPanelEvent e) {
                int index = westTabs.indexOf(e.getPresenter());
                String datakey = westTabs.remove(index).getDataKey();
                view.removeDataTab(index);
                popout(datakey);
            }
        });
    }

    private void loadParsedDataMenu()
    {
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
                    view.addMenuItem(new MenuItem(key, false, getMenuCommand(key)));
                }
            }
        });
    }

	@Override
	public void goTo(Place place) {
		this.places.goTo(place);
	}

    @Override
    public void addDataTab(final String datakey) {
        this.service.getParsedData(datakey, new AsyncCallback<List<Map<String, String>>>() {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.INFO, "Failure adding data tab.");
                logger.log(Level.SEVERE, caught.toString());
                // FIXME: do a message box or something...
            }

            @Override
            public void onSuccess(List<Map<String, String>> result)
            {
                logger.log(Level.INFO, "Building data tab.");
                DataPanelPresenter presenter = new DataPanelPresenterImpl(service, new DataPanelViewImpl(), eventBus, datakey, result);
                view.addDataTab(presenter.getView(), datakey);
                westTabs.add(presenter);
            }
        });
    }

    @Override
    public void addVisTab(final String datakey, final String modelkey, final int tree) {
        this.service.getTreeView(datakey, modelkey, tree, new AsyncCallback<RFTreeView>() {
            @Override
            public void onFailure(Throwable thrwbl) {
                logger.log(Level.SEVERE, thrwbl.toString());
                // FIXME: do a message box or something...
            }

            @Override
            public void onSuccess(RFTreeView treeview) {
                logger.log(Level.INFO, treeview.toString());
                view.addVisTab(
                    new TreePanel(treeview, datakey, modelkey, tree),
                    datakey + "<br>" + modelkey + "<br>tree " + (tree + 1)
                );
            }
        });
    }

    @Override
    public void addConfusionMatrixTab(RF rf) {
        ConfusionMatrixPresenter presenter = new ConfusionMatrixPresenterImpl(new ConfusionMatrixViewImpl(), eventBus, rf);
        String title = "Confusion Matrix<br>" + rf.getDataKey() + "<br>" + rf.getModelKey();
        DoublePanelViewImpl panelView = (DoublePanelViewImpl)view;
                panelView.addVisTab(presenter.getView(), title);
    }

    private Command getMenuCommand(final String value) {
        return new Command() {
            @Override
            public void execute() {
                logger.log(Level.INFO, "Adding dataset: " + value);
                addDataTab(value);
            }
        };
    }

	private void popout(String datakey) {
		logger.log(Level.INFO, "Popping panel: " + datakey);

        PopoutDataPanelPlace place = new PopoutDataPanelPlace();
        place.setDataKey(datakey);

        // FIXME: this needs to be injected...
		AppPlaceHistoryMapper historyMapper = Entry.getPlaceHistoryMapper();

        String token = historyMapper.getToken(place);
        String url = Window.Location.createUrlBuilder().setHash(token).buildString();
        int width = Window.getClientWidth() / 2;
        int height = Window.getClientHeight() / 2;
        String features = "width=" + width + ",height=" + height + ",menubar=0,location=0,toolbar=0,status=0";

        openWindow(this, url, "_blank", features, datakey);
	}

	public void popin(String datakey) {
		logger.log(Level.INFO, "Adding panel back in!");
		addDataTab(datakey);
	}

	private static native void openWindow(DoublePanelActivity parent, String url, String name, String features, String datakey)/*-{
	    var window = $wnd.open(url, name, features);
		window.onbeforeunload = function() {
		    parent.@edu.oswego.csc480_hci521_2013.client.activity.DoublePanelActivity::popin(Ljava/lang/String;)(datakey);
		}
	}-*/;
}
