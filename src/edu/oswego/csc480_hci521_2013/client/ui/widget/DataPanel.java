package edu.oswego.csc480_hci521_2013.client.ui.widget;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;

/**
 *
 */
public class DataPanel extends Composite implements IsWidget {

    private MenuBar visbar;
    private VerticalPanel dataPanel;
    private MenuBar treebar;
    private MenuItem trees;
    private List<Map<String, String>> data;
    private DataPanelPresenter presenter;

    public DataPanel(List<Map<String, String>> data) {
        this.data = data;
    }

    public void buildUi() {
        dataPanel = new VerticalPanel();
        if (data.isEmpty()) {
            dataPanel.add(new HTML("No Data Found"));
        }
        else {
            visbar = new MenuBar(false);
            MenuItem generate = new MenuItem("Generate Forest", presenter.getGenerateCommand());
            visbar.addItem(generate);
            dataPanel.add(visbar);

            CellTable<Map<String, String>> cellTable = new CellTable<Map<String, String>>();
            for (final String key : data.get(0).keySet()) {
                cellTable.addColumn(new TextColumn<Map<String, String>>()
                {
                    @Override
                    public String getValue(Map<String, String> row)
                    {
                        return row.get(key);
                    }
                }, key);
            }
            cellTable.setRowData(data);
            ScrollPanel scroll = new ScrollPanel();
            scroll.setSize((Window.getClientWidth() - 60)/2 + "px", Window.getClientHeight() - 40 - 100 + "px");
            scroll.add(cellTable);
            dataPanel.add(scroll);
        }
        initWidget(dataPanel);
    }

    public void setPresenter(DataPanelPresenter presenter) {
        this.presenter = presenter;
    }

    public void forestStarted() {
        visbar.clearItems();
        treebar = new MenuBar(true);
        trees = new MenuItem("Trees", treebar);
        trees.setEnabled(false);
        visbar.addItem(trees);
    }

    public void setForestStatus(int done, int total) {
        trees.setHTML("Trees: Generated " + done + " of " + total);
    }

    public void forestFinish(int count) {
        trees.setHTML("Trees");
        for (int i = 0; i < count; i++) {
            final int index = i;
            treebar.addItem(String.valueOf(i + 1), presenter.getTreeVisCommand(index));
        }
        trees.setEnabled(true);
    }
}
