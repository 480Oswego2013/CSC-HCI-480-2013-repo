package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.core.client.GWT;
import java.util.Arrays;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.TreeNode;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoublePanelView extends Composite implements PanelView {

	private Presenter presenter;
    static final Logger logger = Logger.getLogger(DoublePanelView.class.getName());
    private final H2OServiceAsync h2oService = GWT.create(H2OService.class);

	TabPanel tpData;
    TabPanel tpVis;
	int imgNum = 0;

	// Add all widgets to the view
	public DoublePanelView() { }

    @Override
    public void addDataTab(final String title)
    {
        logger.log(Level.INFO, "adding data tab: " + title);
        h2oService.getParsedData(title, new AsyncCallback<List<Map<String, String>>>() {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.SEVERE, caught.toString());
                tpData.add(new HTML(caught.getMessage()), title, false);
            }

            @Override
            public void onSuccess(List<Map<String, String>> result)
            {
                if (result.isEmpty()) {
                    tpData.add(new HTML("No Data Found"), title, false);
                }
                else {
                    VerticalPanel dataPanel = new VerticalPanel();
                    MenuBar visbar = new MenuBar(false);
                    visbar.addItem(new MenuItem("Generate Forest", new Command() {
                        @Override
                        public void execute()
                        {
                            logger.log(Level.SEVERE, "TODO!");
                        }
                    }));
                    dataPanel.add(visbar);
                    CellTable<Map<String, String>> cellTable = new CellTable<Map<String, String>>();
                    cellTable.setSize("100%", Window.getClientHeight() - 40 - 100 + "px");
                    for (final String key: result.get(0).keySet()) {
                        cellTable.addColumn(new TextColumn<Map<String, String>>() {
                            @Override
                            public String getValue(Map<String, String> row) {
                                return row.get(key);
                            }
                        }, key);
                    }
                    cellTable.setRowData(result);
                    dataPanel.add(cellTable);
                    tpData.add(dataPanel, title, false);
                }
            }
        });
    }

    private void addDummyDataTab(String title)
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
        tpData.add(cellTable, title, false);
    }

	@Override
	public void addVisTab(String title) {
		final HorizontalPanel panel = new HorizontalPanel();
        final String datakey = "iris1.hex";
        final String modelkey = "iris1.model";
        h2oService.getTreeAsJson(datakey, modelkey, imgNum, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.SEVERE, caught.toString());
                panel.add(new HTML(caught.getMessage()));
            }

            @Override
            public void onSuccess(String result)
            {
                logger.log(Level.INFO, result);
                if (result.isEmpty()) {
                    panel.add(new HTML("No Data Found"));
                }
                else {
                    panel.add(new HTML(result));
                }
            }
        });
		tpVis.add(panel, modelkey + " tree " + imgNum, false);

		imgNum++;
	}

	public void addVisTab2(String title) {
		final HorizontalPanel panel = new HorizontalPanel();
        final String datakey = "iris1.hex";
        final String modelkey = "iris1.model";
        h2oService.getTree(datakey, modelkey, imgNum, new AsyncCallback<TreeNode>() {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.SEVERE, caught.toString());
                panel.add(new HTML(caught.getMessage()));
            }

            @Override
            public void onSuccess(TreeNode result)
            {
                logger.log(Level.INFO, result.toString());
                panel.add(new HTML(result.toString()));
            }
        });
		tpVis.add(panel, modelkey + " tree " + imgNum, false);

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
        mainPanel.setVerticalAlignment(HorizontalPanel.ALIGN_TOP);
		mainPanel.setSpacing(10);

		VerticalPanel leftPanel = new VerticalPanel();
		leftPanel.setSize("100%", "100%");
		leftPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        leftPanel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
		mainPanel.add(leftPanel);

		VerticalPanel rightPanel = new VerticalPanel();
		rightPanel.setSize("100%", "100%");
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

        addDummyDataTab("dummy tab");
		addVisTab("Tab 1");
		addVisTab("Tab 2");
		addVisTab2("Tab 3");
		tpData.selectTab(0);
		tpVis.selectTab(0);

		initWidget(mainPanel);
	}

}
