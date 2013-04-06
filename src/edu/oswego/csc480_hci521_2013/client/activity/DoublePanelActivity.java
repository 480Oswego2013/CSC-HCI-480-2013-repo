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
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.MenuItem;
import edu.oswego.csc480_hci521_2013.client.AppPlaceHistoryMapper;
import edu.oswego.csc480_hci521_2013.client.Entry;

import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEventHandler;
import edu.oswego.csc480_hci521_2013.client.place.ConfusionMatrixPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DataTablePlace;
import edu.oswego.csc480_hci521_2013.client.place.TreeVisPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.presenters.DoublePanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.TabPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.TreePanelPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelView;
import edu.oswego.csc480_hci521_2013.client.ui.TabLabelView;
import edu.oswego.csc480_hci521_2013.client.ui.TabLabelViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.TreePanelViewImpl;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import java.util.ArrayList;
import java.util.HashMap;
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
    private TabManager dataTabs = new TabManager();
    private TabManager visTabs = new TabManager();

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
    }

    private void loadParsedDataMenu() {
        service.getParsedDataKeys(new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                logger.log(Level.SEVERE, caught.toString());
            }

            @Override
            public void onSuccess(List<String> result) {
                for (String key : result) {
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
        logger.log(Level.INFO, "Creating new data tab: " + datakey);
        this.service.getParsedData(datakey, new AsyncCallback<List<Map<String, String>>>() {
            @Override
            public void onFailure(Throwable caught) {
                logger.log(Level.INFO, "Failure adding data tab.");
                logger.log(Level.SEVERE, caught.toString());
                // FIXME: do a message box or something...
            }

            @Override
            public void onSuccess(List<Map<String, String>> result) {
                logger.log(Level.INFO, "Building data tab: " + datakey);
                DataPanelPresenterImpl presenter = new DataPanelPresenterImpl(service, new DataPanelViewImpl(), eventBus, datakey, result);
                TabLabelView label = new TabLabelViewImpl();
                label.setLabel(datakey);
                label.setPresenter(DoublePanelActivity.this);
                view.addDataTab(presenter.getView(), label);
                dataTabs.addTab(label, presenter);
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
                TreePanelPresenterImpl presenter = new TreePanelPresenterImpl(
                        new TreePanelViewImpl(treeview, datakey, modelkey, tree),
                        treeview, datakey, modelkey, tree);
                logger.log(Level.INFO, treeview.toString());
                TabLabelView label = new TabLabelViewImpl();
                label.setLabel(datakey + "<br>" + modelkey + "<br>tree " + (tree + 1));
                label.setPresenter(DoublePanelActivity.this);
                view.addVisTab(presenter.getView(), label);
                visTabs.addTab(label, presenter);
            }
        });
    }

    @Override
    public void addConfusionMatrixTab(RF rf) {
        ConfusionMatrixPresenterImpl presenter = new ConfusionMatrixPresenterImpl(new ConfusionMatrixViewImpl(), eventBus, rf);
        String title = "Confusion Matrix<br>" + rf.getDataKey() + "<br>" + rf.getModelKey();
        TabLabelView label = new TabLabelViewImpl();
        label.setLabel(title);
        label.setPresenter(this);
        view.addVisTab(presenter.getView(), label);
        visTabs.addTab(label, presenter);
    }

    public void addConfusionMatrixTab(RF rf, RFView rfview) {
        ConfusionMatrixPresenterImpl presenter = new ConfusionMatrixPresenterImpl(new ConfusionMatrixViewImpl(), eventBus, rf);
        presenter.setData(rfview);
        String title = "Confusion Matrix<br>" + rf.getDataKey() + "<br>" + rf.getModelKey();
        TabLabelView label = new TabLabelViewImpl();
        label.setLabel(title);
        label.setPresenter(this);
        view.addVisTab(presenter.getView(), label);
        visTabs.addTab(label, presenter);
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

    private void popoutConfusionMatrixTab(ConfusionMatrixPlace place, int id) {
        // FIXME: this needs to be injected...
        AppPlaceHistoryMapper historyMapper = Entry.getPlaceHistoryMapper();

        String token = historyMapper.getToken(place);
        String url = Window.Location.createUrlBuilder().setHash(token).buildString();
        int width = Window.getClientWidth() / 2;
        int height = Window.getClientHeight() / 2;
        String features = "width=" + width + ",height=" + height + ",menubar=0,location=0,toolbar=0,status=0";

        ConfusionMatrixActivity.openPanel(this, url, "_blank", features, Integer.toString(id));
    }

    private void popoutTreeVisTab(TreeVisPlace place, int id) {
        // FIXME: this needs to be injected...
        AppPlaceHistoryMapper historyMapper = Entry.getPlaceHistoryMapper();

        String token = historyMapper.getToken(place);
        String url = Window.Location.createUrlBuilder().setHash(token).buildString();
        int width = Window.getClientWidth() / 2;
        int height = Window.getClientHeight() / 2;
        String features = "width=" + width + ",height=" + height + ",menubar=0,location=0,toolbar=0,status=0";

        TreeVisActivity.openPanel(this, url, "_blank", features, Integer.toString(id));
    }

    private void popoutDataTab(DataTablePlace place, int id) {
        logger.log(Level.INFO, "Popping data panel: " + place.getDataKey());

        // FIXME: this needs to be injected...
        AppPlaceHistoryMapper historyMapper = Entry.getPlaceHistoryMapper();

        String token = historyMapper.getToken(place);
        String url = Window.Location.createUrlBuilder().setHash(token).buildString();
        int width = Window.getClientWidth() / 2;
        int height = Window.getClientHeight() / 2;
        String features = "width=" + width + ",height=" + height + ",menubar=0,location=0,toolbar=0,status=0";

        DataPanelActivity.openPanel(this, url, "_blank", features, Integer.toString(id));
    }

    public void popinDataPanel(String id) {
        logger.log(Level.INFO, "Adding data panel back in!");
        TabLabelView tab = dataTabs.unpopTab(Integer.parseInt(id));
        TabPanelPresenter p = dataTabs.getPresenter(tab);
        // FIXME: reusing presenters/view does not work yet...
        //p.added();
        dataTabs.deleteTab(tab);
        addDataTab(((DataPanelPresenterImpl) p).getDataKey());
    }

    public void popinVisPanel(String id) {
        logger.log(Level.INFO, "Adding vis panel back in!");
        TabLabelView tab = visTabs.unpopTab(Integer.parseInt(id));
        TabPanelPresenter p = visTabs.getPresenter(tab);
        // FIXME: reusing presenters/view does not work yet...
        //p.added();
        visTabs.deleteTab(tab);
        if (p instanceof ConfusionMatrixPresenterImpl) {
            ConfusionMatrixPresenterImpl cp = (ConfusionMatrixPresenterImpl) p;
            addConfusionMatrixTab(cp.getRandomForest(), cp.getData());
        }
        else {
            TreePanelPresenterImpl tp = (TreePanelPresenterImpl) p;
            addVisTab(tp.getDatakey(), tp.getModelkey(), tp.getTreeIndex());
        }
    }

    @Override
    public void popout(TabLabelView tab) {
        if (dataTabs.hasTab(tab)) {
            int index = dataTabs.popTab(tab);
            view.removeDataTab(index);
            TabPanelPresenter p = dataTabs.getPresenter(tab);
            p.removed();
            String datakey = ((DataPanelPresenterImpl) p).getDataKey();
            DataTablePlace place = new DataTablePlace();
            place.setDataKey(datakey);
            popoutDataTab(place, tab.hashCode());
        } else if (visTabs.hasTab(tab)) {
            int index = visTabs.popTab(tab);
            view.removeVisTab(index);
            TabPanelPresenter p = visTabs.getPresenter(tab);
            p.removed();
            if (p instanceof ConfusionMatrixPresenterImpl) {
                ConfusionMatrixPresenterImpl cp = (ConfusionMatrixPresenterImpl) p;
                ConfusionMatrixPlace place = new ConfusionMatrixPlace();
                place.setRandomForest(cp.getRandomForest());
                popoutConfusionMatrixTab(place, tab.hashCode());
            }
            else {
                TreePanelPresenterImpl tp = (TreePanelPresenterImpl) p;
                TreeVisPlace place = new TreeVisPlace();
                place.setDataKey(tp.getDatakey());
                place.setModelKey(tp.getModelkey());
                place.setTree(tp.getTreeIndex());
                popoutTreeVisTab(place, tab.hashCode());
            }
        } else {
            logger.log(Level.SEVERE, "Unknown tab!");
        }
    }

    private static class TabManager {

        List<TabLabelView> tabList = new ArrayList<TabLabelView>();
        Map<TabLabelView, TabPanelPresenter> panels = new HashMap<TabLabelView, TabPanelPresenter>();
        Map<Integer, TabLabelView> popped = new HashMap<Integer, TabLabelView>();

        boolean hasTab(TabLabelView tab) {
            return panels.containsKey(tab);
        }

        TabPanelPresenter getPresenter(TabLabelView tab) {
            return panels.get(tab);
        }

        void addTab(TabLabelView tab, TabPanelPresenter presenter) {
            panels.put(tab, presenter);
            tabList.add(tab);
        }

        int popTab(TabLabelView tab) {
            int index = tabList.indexOf(tab);
            tabList.remove(tab);
            popped.put(tab.hashCode(), tab);
            return index;
        }

        TabLabelView unpopTab(Integer id) {
            TabLabelView t = popped.remove(id);
            tabList.add(t);
            return t;
        }

        void deleteTab(TabLabelView tab) {
            tabList.remove(tab);
            panels.remove(tab);
        }
    }
}
