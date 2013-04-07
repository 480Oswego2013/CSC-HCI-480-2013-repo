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
package edu.oswego.csc480_hci521_2013.client.services;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFViewBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders
        .RedirectRequestFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class RFViewPoller {

    static final Logger logger = Logger.getLogger(RFViewPoller.class.getName());
    static final int DELAY = 1000;

    private final EventBus eventbus;
    private final H2OServiceAsync h2oService;
    private final RF randomForest;
    private final Timer timer;
    private RFViewBuilder builder;

    public RFViewPoller(EventBus eventbus, H2OServiceAsync h2oService, RF randomForest) {
        this.eventbus = eventbus;
        this.h2oService = h2oService;
        this.randomForest = randomForest;

        try {
            builder = (RFViewBuilder) RedirectRequestFactory.getRequest(
                    randomForest.getResponse());
        } catch (Exception e) {
            logger.warning("Failed to build request from response");
            builder = new RFViewBuilder(randomForest);
        }

        timer = new Timer() {
            @Override
            public void run() {
                poll();
            }
        };
    }

    public void start() {
        poll();
    }

    void poll() {
        logger.log(Level.INFO, "Polling forest generation progress");
        h2oService.getRandomForestView(builder, new AsyncCallback<RFView>() {
            @Override
            public void onFailure(final Throwable thrwbl) {
                // FIXME: need a way of handling errors...
                logger.log(Level.SEVERE, thrwbl.toString());
            }

            @Override
            public void onSuccess(final RFView rfview) {
                eventbus.fireEvent(new RFProgressEvent(rfview));
                if (rfview.getResponse().isPoll()) {
                    timer.schedule(DELAY);
                }
            }
        });
    }
}
