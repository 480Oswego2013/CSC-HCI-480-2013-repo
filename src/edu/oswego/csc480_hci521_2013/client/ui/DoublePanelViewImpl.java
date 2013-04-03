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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoublePanelViewImpl extends Composite implements DoublePanelView
{
    interface Binder extends UiBinder<Widget, DoublePanelViewImpl> {
    }

    private static Binder uiBinder = GWT.create(Binder.class);
    static final Logger logger = Logger.getLogger(DoublePanelViewImpl.class.getName());

    @UiField MenuBar dataSets;
    @UiField TabLayoutPanel tpData;
    @UiField TabLayoutPanel tpVis;

    Label dummyDataLabel = new Label("dummy tab", false);
    Label dummyVisLabel = new Label("dummy tab", false);

    public DoublePanelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        tpData.getTabWidget(0).getParent().setVisible(false);
        tpVis.getTabWidget(0).getParent().setVisible(false);
    }

    @Override
    public void addDataTab(IsWidget panel, IsWidget title)
    {
        logger.log(Level.INFO, "adding data tab: " + title);
        tpData.add(panel, title);
        tpData.selectTab(tpData.getWidgetCount() - 1);
        if (dummyDataLabel != null) {
            tpData.remove(0);
            dummyDataLabel = null;
        }
    }

    @Override
    public void removeDataTab(int index) {
        tpData.remove(index);
    }

    @Override
    public void addVisTab(IsWidget panel, IsWidget title) {
        logger.log(Level.INFO, "adding vis tab: " + title);
        tpVis.add(panel, title);
        tpVis.selectTab(tpVis.getWidgetCount() - 1);
        if (dummyVisLabel != null) {
            tpVis.remove(0);
            dummyVisLabel = null;
        }
    }

    @Override
    public void removeVisTab(int index) {
        tpVis.remove(index);
    }

    @Override
    public void addMenuItem(MenuItem item) {
        dataSets.addItem(item);
    }
}
