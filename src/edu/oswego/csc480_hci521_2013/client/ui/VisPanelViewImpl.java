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

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TabPanel;

import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter.TabView;
import edu.oswego.csc480_hci521_2013.client.presenters.VisPanelPresenter;

public class VisPanelViewImpl extends Composite implements
		VisPanelPresenter.View {

	VisPanelPresenter presenter;

	TabPanel tpVis;
	static final Logger logger = Logger.getLogger(VisPanelViewImpl.class
			.getName());

	public VisPanelViewImpl() {
		buildUi();
	}

	@Override
	public void buildUi() {
		tpVis = new TabPanel();
		tpVis.setSize("100%", "100%");
		tpVis.setAnimationEnabled(true);
		
		initWidget(tpVis);
	}

	@Override
	public void setPresenter(VisPanelPresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public int getActiveTabIndex() {
		return tpVis.getTabBar().getSelectedTab();
	}

	@Override
	public TabView getTab(int index) {
		return (TabView) tpVis.getWidget(index);
	}
	
	@Override
	public void clear() {
		tpVis.clear();
	}
	
	@Override
	public int getTabCount() {
		return tpVis.getWidgetCount();
	}

	@Override
	public void addVisTab(IsWidget panel, String title) {
		tpVis.add(panel, title, true);
		tpVis.selectTab(tpVis.getWidgetCount() - 1);
	}
	
	@Override
	public void removeTab(int index) {
		tpVis.remove(index);
		if(tpVis.getWidgetCount()>0)
			tpVis.selectTab(tpVis.getWidgetCount()-1);
	}

}
