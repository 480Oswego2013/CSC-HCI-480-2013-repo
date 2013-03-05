package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import java.util.Arrays;

import edu.oswego.csc480_hci521_2013.client.Entry;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
import java.util.List;
import java.util.Map;
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

    // Add all widgets to the view
    public DoublePanelView()
    {
    }

    @Override
    public void addDataTab(final String datakey)
    {
        // FIXME: nesting!
        logger.log(Level.INFO, "adding data tab: " + datakey);
        h2oService.getParsedData(datakey, new AsyncCallback<List<Map<String, String>>>()
        {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.SEVERE, caught.toString());
                tpData.add(new HTML(caught.getMessage()), datakey, false);
            }

            @Override
            public void onSuccess(List<Map<String, String>> result)
            {
                if (result.isEmpty()) {
                    tpData.add(new HTML("No Data Found"), datakey, false);
                }
                else {
                    VerticalPanel dataPanel = new VerticalPanel();
                    final MenuBar visbar = new MenuBar(false);
                    final MenuItem generate = new MenuItem("Generate Forest", new Command()
                    {
                        @Override
                        public void execute()
                        {
                            logger.log(Level.INFO, "Generating Forest");
                            h2oService.generateRandomForest(datakey, new AsyncCallback<String>() {
                                @Override
                                public void onFailure(Throwable thrwbl)
                                {
                                    logger.log(Level.SEVERE, thrwbl.toString());
                                }

                                @Override
                                public void onSuccess(final String modelkey)
                                {
                                    logger.log(Level.INFO, "Forest Started");
                                    visbar.clearItems();
                                    final MenuBar treebar = new MenuBar(true);
                                    final MenuItem trees = new MenuItem("Trees", treebar);
                                    trees.setEnabled(false);
                                    visbar.addItem(trees);

                                    Scheduler.get().scheduleFixedPeriod(new Scheduler.RepeatingCommand() {
                                        boolean isFinished = false;
                                        @Override
                                        public boolean execute()
                                        {
                                            logger.log(Level.INFO, "Polling forest generation progress");
                                            if (isFinished) {
                                                return false;
                                            }

                                            h2oService.getRandomForestView(datakey, modelkey, new AsyncCallback<RFView>() {
                                                @Override
                                                public void onFailure(Throwable thrwbl)
                                                {
                                                    logger.log(Level.SEVERE, thrwbl.toString());
                                                }

                                                @Override
                                                public void onSuccess(RFView rfview)
                                                {
                                                    logger.log(Level.INFO, rfview.toString());
                                                    ResponseStatus status = rfview.getResponse();
                                                    if (status.isPoll()) {
                                                        int done = status.getProgress();
                                                        int total = status.getProgressTotal();
                                                        logger.log(Level.INFO, "Trees generated " + done + " of " + total);
                                                        trees.setHTML("Trees: Generated " + done + " of " + total);
                                                    }
                                                    else {
                                                        logger.log(Level.INFO, "Forest finished");
                                                        trees.setHTML("Trees");
                                                        trees.setEnabled(true);
                                                        isFinished = true;
                                                        int total = rfview.getNtree();
                                                        for (int i = 0; i < total; i++) {
                                                            final int index = i;
                                                            treebar.addItem(String.valueOf(i + 1), new Command() {
                                                                @Override
                                                                public void execute()
                                                                {
                                                                    addVisTab(datakey, modelkey, index);
                                                                }
                                                            });
                                                        }
                                                    }
                                                }
                                            });
                                            return true;
                                        }
                                    }, 1000);
                                }
                            });
                        }
                    });
                    visbar.addItem(generate);
                    dataPanel.add(visbar);
                    CellTable<Map<String, String>> cellTable = new CellTable<Map<String, String>>();
                    cellTable.setSize("100%", Window.getClientHeight() - 40 - 100 + "px");
                    for (final String key : result.get(0).keySet()) {
                        cellTable.addColumn(new TextColumn<Map<String, String>>()
                        {
                            @Override
                            public String getValue(Map<String, String> row)
                            {
                                return row.get(key);
                            }
                        }, key);
                    }
                    cellTable.setRowData(result);
                    dataPanel.add(cellTable);
                    tpData.add(dataPanel, datakey, false);
                    tpData.selectTab(tpData.getWidgetCount() - 1);
                    if (dummyDataLabel != null) {
                        tpData.remove(0);
                        dummyDataLabel = null;
                    }
                }
            }
        });
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
    public void addVisTab(final String datakey, final String modelkey, final int tree)
    {
        final HorizontalPanel panel = new HorizontalPanel();
        h2oService.getTreeAsJson(datakey, modelkey, imgNum, new AsyncCallback<String>()
        {
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
                String canvas = (datakey + "__" + modelkey + "__" + tree).replaceAll("\\.", "_");
                // FIXME: we dont want to have a fixed size but it wont work without it at this point.
                panel.add(new HTML("<div id=\"" + canvas + "\" class=\"sigma-expand\" style=\"width:800px;height:1000px;\"></div>"));
                Entry.callSigma(canvas, result);
            }
        });
        tpVis.add(panel, datakey + "<br>" + modelkey + "<br>tree " + (tree + 1), true);
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

        // FIXME: if we dont start with tabs the tab panel does not work...
        addDummyDataTab();
        addDummyVisTab();
        tpData.selectTab(0);
        tpVis.selectTab(0);

        initWidget(mainPanel);
    }
}
