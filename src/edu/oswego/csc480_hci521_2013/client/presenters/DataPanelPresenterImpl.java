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

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.services.RFViewPoller;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class DataPanelPresenterImpl implements DataPanelPresenter {

    static final Logger logger = Logger.getLogger(DataPanelPresenterImpl.class.getName());
    EventBus eventbus;
    View view;
    H2OServiceAsync h2oService;
    String datakey;
    RF randomForest;

    public DataPanelPresenterImpl(ClientFactory factory, String datakey, List<Map<String, String>> data) {
        this.eventbus = factory.getEventBus();
        this.view = factory.getDataPanelPresenterView();
        this.h2oService = factory.getH2OService();
        this.datakey = datakey;

        view.setPresenter(this);
        view.setData(data);
        view.buildUi();
        bind();
    }

    private void bind() {
        eventbus.addHandler(RFProgressEvent.TYPE, new RFProgressEventHandler() {
            @Override
            public void onDataUpdate(RFProgressEvent e) {
                if (isOurData(e.getData())) {
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
        });
    }

    private boolean isOurData(RFView data) {
        if (data.getDataKey().equals(randomForest.getDataKey())
                && data.getModelKey().equals(randomForest.getModelKey())) {
            return true;
        }
        return false;
    }

    @Override
    public Command getGenerateCommand() {
        return new Command() {
            @Override
            public void execute() {
                logger.log(Level.INFO, "Generating Forest");
                h2oService.generateRandomForest(new RFBuilder(datakey).setNtree(1000), new AsyncCallback<RF>() {
                    @Override
                    public void onFailure(Throwable thrwbl) {
                        logger.log(Level.SEVERE, thrwbl.toString());
                        // FIXME: do a message box or something...
                    }

                    @Override
                    public void onSuccess(RF rf) {
                        logger.log(Level.INFO, "Forest Started");
                        randomForest = rf;

                        eventbus.fireEvent(new RFGenerateEvent(rf));

                        view.forestStarted();
                        new RFViewPoller(eventbus, h2oService, randomForest).start();
                    }
                });
            }
        };
    }

    @Override
    public Command getTreeVisCommand(final int index) {
        return new Command() {
            @Override
            public void execute()
            {
                eventbus.fireEvent(new TreeVisEvent(randomForest, index));
            }
        };
    }

    @Override
    public View getView() {
        return view;
    }
}
