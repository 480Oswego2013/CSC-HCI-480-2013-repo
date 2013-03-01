package edu.oswego.csc480_hci521_2013.client.ui;

import java.util.Arrays;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DoublePanelView extends Composite implements PanelView {

	private Presenter presenter;

	TabPanel tpData, tpVis;
	int imgNum = 0;

	// Add all widgets to the view
	public DoublePanelView() {

	}

	@Override
	public void addDataTab(String title) {
		CellTable<String> cellTable = new CellTable<String>();
		cellTable.setSize("100%", Window.getClientHeight() - 40 - 100 + "px");
		for (int i = 0; i < 5; i++)
			cellTable.addColumn(new TextColumn<String>() {
				@Override
				public String getValue(String object) {
					return object;
				}
			}, "Column " + i);
		cellTable.setRowData(Arrays.asList(new String[] { "Data1", "Data2",
				"Data3", "Data4", "Data5", "Data6", "Data7", "Data8", "Data9",
				"Data10", "Data11", "Data12", "Data13", "Data14", "Data15" }));
		tpData.add(cellTable, title, false);
	}

	@Override
	public void addVisTab(String title) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		panel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
		panel.setSize("100%", Window.getClientHeight() - 40 - 100 + "px");
		Image img = imgNum == 0 ? new Image("img0.jpg") : new Image("img1.png");
		img.setSize("500px", "300px");
		panel.add(img);
		tpVis.add(panel, title, false);
		
		imgNum++;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void buildGui() {
		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.setSize("100%", "100%");
		mainPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		mainPanel.setSpacing(10);

		VerticalPanel leftPanel = new VerticalPanel();
		leftPanel.setSize("100%", "100%");
		leftPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		mainPanel.add(leftPanel);

		VerticalPanel rightPanel = new VerticalPanel();
		rightPanel.setSize("100%", "100%");
		rightPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
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

		addDataTab("Tab 1");
		addDataTab("Tab 2");
		addDataTab("Tab 3");
		addVisTab("Tab 1");
		addVisTab("Tab 2");
		tpData.selectTab(0);
		tpVis.selectTab(0);

		initWidget(mainPanel);
	}

}
