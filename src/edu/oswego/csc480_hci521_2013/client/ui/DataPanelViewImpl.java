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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter.TabView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;

public class DataPanelViewImpl extends Composite implements
		DataPanelPresenter.View {

	DataPanelPresenter presenter;

	TabPanel tpData;
    TabPanel tpVis;
	static final Logger logger = Logger.getLogger(DoublePanelView.class
			.getName());

	public DataPanelViewImpl() {
		buildUi();
	}

	@Override
	public void buildUi() {
		tpData = new TabPanel();
		tpData.setSize("100%", "100%");
		tpData.setAnimationEnabled(true);

		tpVis = new TabPanel();
		tpVis.setSize("100%", "100%");
		tpVis.setAnimationEnabled(true);
		
		initWidget(tpData);
	}

	@Override
	public void setPresenter(DataPanelPresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public int getActiveTabIndex() {
		return tpData.getTabBar().getSelectedTab();
	}

	@Override
	public TabView getTab(int index) {
		return (TabView) tpData.getWidget(index);
	}
	
	@Override
	public void clear() {
		tpData.clear();
	}
	
	@Override
	public int getTabCount() {
		return tpData.getWidgetCount();
	}

	@Override
	public void addDataTab(String title, List<Map<String, String>> data) {
		logger.log(Level.INFO, "adding data tab: " + title);

		TabView tab = new DataPanelTabViewImpl(data, title);
		tab.setPresenter(presenter);
		tab.buildUi();
		tpData.add(tab, title, false);
		tpData.selectTab(tpData.getWidgetCount() - 1);
	}
	
	@Override
	public void addDataTab(String title, TabView tab) {
		logger.log(Level.INFO, "adding data tab: " + title);
		
		tpData.add(tab, title, false);
		tpData.selectTab(tpData.getWidgetCount() - 1);
	}
	
	@Override
	public void removeDataTab(int index) {
		tpData.remove(index);
		if(tpData.getWidgetCount()>0)
			tpData.selectTab(tpData.getWidgetCount()-1);
	}

    @Override
    public void addVisTab(ConfusionMatrixView panel, String title) {
        logger.log(Level.INFO, "adding vis tab: " + title);
        tpVis.add(panel, title, true);
        tpVis.selectTab(tpVis.getWidgetCount() - 1);
    }
}
