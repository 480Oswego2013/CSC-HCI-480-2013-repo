package edu.oswego.csc480_hci521_2013.client.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
//		VerticalPanel vp = new VerticalPanel();
		tpData = new TabPanel();
		tpData.setSize("100%", "100%");
		tpData.setAnimationEnabled(true);
		
//		addDummyDataTab();
//		vp.add(new Label("TEST!"));
//		vp.add(tpData);
//		tpData.selectTab(0);
		
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
	public void addDataTab(String title, List<Map<String, String>> data) {
		logger.log(Level.INFO, "adding data tab: " + title);

		TabView tab = new DataPanelTabViewImpl(data);
		tab.setPresenter(presenter);
		tab.buildUi();
		tpData.add(tab, title, false);
		tpData.selectTab(tpData.getWidgetCount() - 1);
	}

	
	
	
	private void addDummyDataTab() {
		CellTable<String> cellTable = new CellTable<String>();
		cellTable.setSize("100%", Window.getClientHeight() - 40 - 100 + "px");
		for (int i = 0; i < 5; i++) {
			cellTable.addColumn(new TextColumn<String>() {
				@Override
				public String getValue(String object) {
					return object;
				}
			}, "Column " + i);
		}
		cellTable.setRowData(Arrays.asList(new String[] { "Data1", "Data2",
				"Data3", "Data4", "Data5", "Data6", "Data7", "Data8", "Data9",
				"Data10", "Data11", "Data12", "Data13", "Data14", "Data15" }));
		tpData.add(cellTable, new Label("dummy tab", false));
	}

}