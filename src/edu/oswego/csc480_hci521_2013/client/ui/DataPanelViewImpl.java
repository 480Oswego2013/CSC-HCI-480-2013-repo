package edu.oswego.csc480_hci521_2013.client.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter.TabView;

public class DataPanelViewImpl extends Composite implements
		DataPanelPresenter.View {

	DataPanelPresenter presenter;

	TabPanel tpData;
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
		
		initWidget(tpData);
	}

	@Override
	public void setPresenter(DataPanelPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public TabView getActivePanel() {
		int index = tpData.getTabBar().getSelectedTab();
		return (TabView) tpData.getWidget(index);
	}
	
	@Override
	public String getTabTitle(TabView tab) {
		int index = tpData.getWidgetIndex(tab);
		return ((TabView)tpData.getWidget(index)).getTabTitle();
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
	public void removeDataTab(TabView tab) {
		int index = tpData.getWidgetIndex(tab);
		tpData.remove(index);
		if(tpData.getWidgetCount()>0)
			tpData.selectTab(tpData.getWidgetCount()-1);
	}

}