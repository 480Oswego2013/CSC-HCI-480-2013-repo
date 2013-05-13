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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import edu.oswego.csc480_hci521_2013.client.place.TreeVisPlace;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.TreePanelViewImpl;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFTreeViewBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class TreeVisActivity extends AbstractActivity {

    static final Logger logger = Logger.getLogger(
        TreeVisActivity.class.getName());

    H2OServiceAsync h2oService;
    String datakey;
    String modelkey;
    int tree;

    public TreeVisActivity(TreeVisPlace place, H2OServiceAsync h2oService) {
        this.h2oService = h2oService;
        datakey = place.getDataKey();
        modelkey = place.getModelKey();
        tree = place.getTree();
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        h2oService.getTreeView(new RFTreeViewBuilder(datakey, modelkey)
                .setTreeNumber(tree),
                new AsyncCallback<RFTreeView>() {
            @Override
            public void onFailure(Throwable thrwbl) {
                logger.log(Level.SEVERE, thrwbl.toString());
                // FIXME: do a message box or something...
            }

            @Override
            public void onSuccess(RFTreeView treeview) {
                logger.log(Level.INFO, treeview.toString());
                panel.setWidget(
                    new TreePanelViewImpl(treeview, datakey, modelkey, tree, true)
                );
            }
        });
    }

    public static native void openPanel(DoublePanelActivity parent, String url, String name, String features, String datakey)/*-{
        var window = $wnd.open(url, name, features);
        window.onbeforeunload = function() {
            parent.@edu.oswego.csc480_hci521_2013.client.activity.DoublePanelActivity::popinVisPanel(Ljava/lang/String;)(datakey);
        }
    }-*/;
}
