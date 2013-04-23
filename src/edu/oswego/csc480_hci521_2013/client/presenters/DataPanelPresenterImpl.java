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
package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFParameterEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFParameterEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.services.RFViewPoller;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelView;
import edu.oswego.csc480_hci521_2013.client.ui.RfParametersViewImpl;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.Inspect;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.InspectBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPanelPresenterImpl implements DataPanelPresenter, TabPanelPresenter {

    static final Logger logger = Logger.getLogger(DataPanelPresenterImpl.class.getName());
    EventBus eventbus;
    DataPanelView view;
    H2OServiceAsync h2oService;
    String datakey;
    RF randomForest;
    Inspect data;
    RfParametersPresenter popUp;


    private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();

    public DataPanelPresenterImpl(H2OServiceAsync service,
            DataPanelView panelView, EventBus eventBus, String datakey,
            Inspect data) {
        this.eventbus = eventBus;
        this.view = panelView;
        this.h2oService = service;
        this.datakey = datakey;
        this.data = data;

        view.setPresenter(this);
        view.setGenerateCommand(getGenerateCommand());

        Set<String> colNames = new HashSet<String>();
        for (Inspect.Column col : data.getCols()) {
            colNames.add(col.getName());
        }
        view.setColumns(colNames);

        // FIXME: This most likely will not return all rows, we need to implement paging
        List<Map<String, String>> rowData = new ArrayList<Map<String, String>>();
        for (Inspect.Row row : data.getRows()) {
            Map<String, String> rowMap = new HashMap<String, String>();
            rowData.add(rowMap);
            for (Inspect.Column column : data.getCols()) {
                rowMap.put(column.getName(), row.getData(column.getName()).toString());
            }
        }
        view.setData(rowData);
        bind();
    }

    @Override
    public String getDataKey() {
        return datakey;
    }

    @Override
    public void added()
    {
        bind();
    }

    @Override
    public void removed()
    {
        for (HandlerRegistration h: handlers) {
            h.removeHandler();
        }
        handlers.clear();
    }

    private void bind() {
        handlers.add(eventbus.addHandler(RFProgressEvent.TYPE, new RFProgressEventHandler() {
            @Override
            public void onDataUpdate(RFProgressEvent e) {
                if (e.getSource().equals(randomForest)) {
                    RFView rfview = e.getData();
                    logger.log(Level.INFO, rfview.toString());
                    ResponseStatus status = rfview.getResponse();
                    if (status.isPoll()) {
                        int done = status.getProgress();
                        int total = status.getProgressTotal();
                        logger.log(Level.INFO, "Trees: Generated " + done + " of " + total);
                        view.setForestStatus(done, total);
                    }
                    else {
                        logger.log(Level.INFO, "Forest finished");
                        view.forestFinish(rfview.getNtree());
                    }
                }
            }
        }));

        handlers.add(eventbus.addHandler(RFParameterEvent.TYPE, new RFParameterEventHandler() {
            @Override
            public void onParams(RFParameterEvent event) {
                if (!event.getSource().equals(popUp)) {
                    return;
                }
                RFBuilder builder = event.getBuilder();
                h2oService.generateRandomForest(builder, new AsyncCallback<RF>() {
                    @Override
                    public void onFailure(Throwable thrwbl) {
                        logger.log(Level.SEVERE, thrwbl.toString());
                        popUp.getView().setError(thrwbl.getMessage());
                    }

                    @Override
                    public void onSuccess(RF rf) {
                        // TODO: this needs some sort of source to know it can from this presenter
                        //       or we just get all events from all dialogs...
                        logger.log(Level.INFO, "Forest Started");
                        randomForest = rf;

                        eventbus.fireEvent(new RFGenerateEvent(rf));

                        view.forestStarted();
                        new RFViewPoller(eventbus, h2oService, randomForest)
                                .start();
                        popUp.getView().hidePopup();
                    }
                });
            }
        }));
    }

    ScheduledCommand getGenerateCommand() {
        return new ScheduledCommand() {
            @Override
            public void execute() {
                logger.log(Level.INFO, "Generating Forest");
                popUp = new RfParametersPresenterImpl(datakey,
                        new RfParametersViewImpl(), eventbus, h2oService);

                h2oService.getData(new InspectBuilder(datakey).setOffset(-1L),
                        new AsyncCallback<Inspect>() {
                    @Override
                    public void onFailure(final Throwable caught) {
                        logger.log(Level.SEVERE, caught.toString());
                        // FIXME: do a message box or something...
                    }

                    @Override
                    public void onSuccess(final Inspect result) {
                        logger.log(Level.INFO, "Headers received");
                        popUp.getView().setColumnInfo(result.getCols());
                    }
                });

                popUp.getView().showPopUp();
            }
        };
    }

    @Override
    public ScheduledCommand getTreeVisCommand(final int index) {
        return new ScheduledCommand() {
            @Override
            public void execute()
            {
                eventbus.fireEvent(new TreeVisEvent(randomForest, index));
            }
        };
    }

    @Override
    public DataPanelView getView() {
        return view;
    }
}
