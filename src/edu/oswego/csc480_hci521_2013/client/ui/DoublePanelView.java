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
import java.util.Arrays;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoublePanelView extends Composite implements PanelView
{
    private Presenter presenter;
    static final Logger logger = Logger.getLogger(DoublePanelView.class.getName());
    private final H2OServiceAsync h2oService = GWT.create(H2OService.class);
    TabPanel tpData;
    TabPanel tpVis;
    int imgNum = 0;

    Label dummyDataLabel = new Label("dummy tab", false);
    Label dummyVisLabel = new Label("dummy tab", false);

    public DoublePanelView() {
    	buildGui();
    }

    @Override
    public void addDataTab(IsWidget panel, String title)
    {
        logger.log(Level.INFO, "adding data tab: " + title);
        tpData.add(panel, title, false);
        tpData.selectTab(tpData.getWidgetCount() - 1);
        if (dummyDataLabel != null) {
            tpData.remove(0);
            dummyDataLabel = null;
        }
    }

    private void addDummyDataTab()
    {
        CellTable<String> cellTable = new CellTable<String>();
        cellTable.setSize("100%", Window.getClientHeight() - 40 - 100 + "px");
        for (int i = 0; i < 5; i++) {
            cellTable.addColumn(new TextColumn<String>()
            {
                @Override
                public String getValue(String object)
                {
                    return object;
                }
            }, "Column " + i);
        }
        cellTable.setRowData(Arrays.asList(new String[] {"Data1", "Data2",
            "Data3", "Data4", "Data5", "Data6", "Data7", "Data8", "Data9",
            "Data10", "Data11", "Data12", "Data13", "Data14", "Data15"}));
        tpData.add(cellTable, dummyDataLabel);
    }

    @Override
    public void addVisTab(IsWidget panel, String title) {
        logger.log(Level.INFO, "adding vis tab: " + title);
        tpVis.add(panel, title, true);
        tpVis.selectTab(tpVis.getWidgetCount() - 1);
        if (dummyVisLabel != null) {
            tpVis.remove(0);
            dummyVisLabel = null;
        }
    }

    private void addDummyVisTab()
    {
        HorizontalPanel panel = new HorizontalPanel();
        panel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
        panel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
        panel.setSize("100%", Window.getClientHeight() - 40 - 100 + "px");
        Image img = new Image("img0.jpg");
        img.setSize("500px", "300px");
        panel.add(img);
        tpVis.add(panel, dummyVisLabel);
    }

    @Override
    public void setPresenter(Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void buildGui()
    {
        HorizontalPanel mainPanel = new HorizontalPanel();
        mainPanel.setSize("100%", "100%");
        mainPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
        mainPanel.setVerticalAlignment(HorizontalPanel.ALIGN_TOP);
        mainPanel.setSpacing(10);

        VerticalPanel leftPanel = new VerticalPanel();
        leftPanel.setSize((Window.getClientWidth() - 60)/2 + "px", Window.getClientHeight() - 40 - 100 + "px");
        leftPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        leftPanel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
        mainPanel.add(leftPanel);

        VerticalPanel rightPanel = new VerticalPanel();
        rightPanel.setSize((Window.getClientWidth() - 60)/2 + "px", Window.getClientHeight() - 40 - 100 + "px");
        rightPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        rightPanel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
        mainPanel.add(rightPanel);

        Label lblData = new Label("Data");
        leftPanel.add(lblData);

        Label lblVis = new Label("Visualization");
        rightPanel.add(lblVis);

        tpData = new TabPanel();
        tpData.setSize("100%", "100%");
        tpData.setAnimationEnabled(true);
        leftPanel.add(tpData);

        tpVis = new TabPanel();
        tpVis.setSize("100%", "100%");
        tpVis.setAnimationEnabled(true);
        rightPanel.add(tpVis);

        // FIXME: if we dont start with tabs the tab panel does not work...
        addDummyDataTab();
        addDummyVisTab();
        tpData.selectTab(0);
        tpVis.selectTab(0);

        initWidget(mainPanel);
    }
}
