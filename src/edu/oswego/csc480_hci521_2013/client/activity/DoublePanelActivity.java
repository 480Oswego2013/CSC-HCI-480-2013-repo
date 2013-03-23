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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import static edu.oswego.csc480_hci521_2013.client.activity.DoublePanelActivity.logger;
import edu.oswego.csc480_hci521_2013.client.events.InspectDataEvent;
import edu.oswego.csc480_hci521_2013.client.events.InspectDataEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEventHandler;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelView;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelView;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;
import edu.oswego.csc480_hci521_2013.client.ui.TreePanel;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoublePanelActivity extends AbstractActivity implements PanelView.Presenter {

    static final Logger logger = Logger.getLogger(DoublePanelActivity.class.getName());
	private PanelView view;
    private PlaceController places;
    private EventBus eventBus;
    private H2OServiceAsync service;

	public DoublePanelActivity(PanelView view, PlaceController places, EventBus eventBus, H2OServiceAsync service) {
		this.view = view;
        this.places = places;
        this.eventBus = eventBus;
        this.service = service;
        logger.log(Level.INFO, "DoublePanelActivity bus: " + eventBus.hashCode());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		this.view.setPresenter(this);
		this.view.buildGui();

		containerWidget.setWidget(this.view.asWidget());
        bind();
        logger.log(Level.INFO, "DoublePanelActivity started");
	}

	@Override
	public String mayStop() {
//		return "Please don't leave me.";
		return null;
	}

    private void bind() {
        eventBus.addHandler(InspectDataEvent.TYPE, new InspectDataEventHandler() {
            @Override
            public void onViewData(InspectDataEvent e) {
                logger.log(Level.INFO, "Adding dataset: " + e.getName());
                addDataTab(e.getName());
            }
        });
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
    }

	// Presenter methods

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
                // TODO: inject this here...
                DoublePanelView panelView = (DoublePanelView)view;
                panelView.addVisTab(
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
        DoublePanelView panelView = (DoublePanelView)view;
                panelView.addVisTab(presenter.getView(), title);
    }
}
