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

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class DataPanelViewImpl extends Composite implements DataPanelPresenter.View {

    private MenuBar visbar;
    private VerticalPanel dataPanel;
    private MenuBar treebar;
    private MenuItem trees;
    private List<Map<String, String>> data;
    private DataPanelPresenter presenter;

    public DataPanelViewImpl() {
    }

    @Override
    public void buildUi() {
        dataPanel = new VerticalPanel();
        if (data.isEmpty()) {
            dataPanel.add(new HTML("No Data Found"));
        } else {
            visbar = new MenuBar(false);
            MenuItem generate = new MenuItem("Generate Forest", presenter.getGenerateCommand());
            visbar.addItem(generate);
            dataPanel.add(visbar);

            CellTable<Map<String, String>> cellTable = new CellTable<Map<String, String>>();
            for (final String key : data.get(0).keySet()) {
                cellTable.addColumn(new TextColumn<Map<String, String>>() {
                    @Override
                    public String getValue(Map<String, String> row) {
                        return row.get(key);
                    }
                }, key);
            }
            cellTable.setRowData(data);
            ScrollPanel scroll = new ScrollPanel();
            scroll.setSize((Window.getClientWidth() - 60) / 2 + "px", Window.getClientHeight() - 40 - 100 + "px");
            scroll.add(cellTable);
            dataPanel.add(scroll);
        }
        initWidget(dataPanel);
    }

    @Override
    public void setPresenter(DataPanelPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void forestStarted() {
        visbar.clearItems();
        treebar = new MenuBar(true);
        trees = new MenuItem("Trees", treebar);
        trees.setEnabled(false);
        visbar.addItem(trees);
    }

    @Override
    public void setForestStatus(int done, int total) {
        trees.setHTML("Trees: Generated " + done + " of " + total);
    }

    @Override
    public void forestFinish(int count) {
        trees.setHTML("Trees");
        for (int i = 0; i < count; i++) {
            final int index = i;
            treebar.addItem(String.valueOf(i + 1), presenter.getTreeVisCommand(index));
        }
        trees.setEnabled(true);
    }

    @Override
    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
}
