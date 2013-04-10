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
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import edu.oswego.csc480_hci521_2013.client.place.ConfusionMatrixPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.services.RFViewPoller;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixViewImpl;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import java.util.logging.Logger;

/**
 *
 */
public class ConfusionMatrixActivity extends AbstractActivity {

    static final Logger logger = Logger.getLogger(
        ConfusionMatrixActivity.class.getName());

    H2OServiceAsync h2oService;
    RF randomForest;

    public ConfusionMatrixActivity(ConfusionMatrixPlace place, H2OServiceAsync h2oService) {
        this.h2oService = h2oService;
        this.randomForest = place.getRandomForest();
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        ConfusionMatrixPresenter presenter = new ConfusionMatrixPresenterImpl(
                new ConfusionMatrixViewImpl(), eventBus, randomForest);
        panel.setWidget(presenter.getView());
        new RFViewPoller(eventBus, h2oService, randomForest).start();
    }

    public static native void openPanel(DoublePanelActivity parent, String url, String name, String features, String datakey)/*-{
        var window = $wnd.open(url, name, features);
        window.onbeforeunload = function() {
            parent.@edu.oswego.csc480_hci521_2013.client.activity.DoublePanelActivity::popinVisPanel(Ljava/lang/String;)(datakey);
        }
    }-*/;
}
