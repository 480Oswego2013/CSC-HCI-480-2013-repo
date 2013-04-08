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
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataPanelViewImpl extends Composite implements DataPanelView {

    interface GridResources extends DataGrid.Resources {
        @Source({DataGrid.Style.DEFAULT_CSS, "DataPanelViewImpl.css"})
        GridStyle dataGridStyle();
    }

    interface GridStyle extends DataGrid.Style {
    }

    interface Binder extends UiBinder<Widget, DataPanelViewImpl> {
    }
    private static Binder uiBinder = GWT.create(Binder.class);
    private DataPanelPresenter presenter;
    @UiField
    MenuBar visbar;
    @UiField
    MenuItem generate;
    @UiField
    MenuBar treebar;
    @UiField
    MenuItem trees;
    @UiField
    DataGrid<Map<String, String>> dataTable;

    @UiFactory
    DataGrid<Map<String, String>> makeDataGrid() {
        DataGrid.Resources resources = GWT.create(GridResources.class);
        return new DataGrid<Map<String, String>>(15, resources);
    }

    public DataPanelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        dataTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);
    }

    @Override
    public void setGenerateCommand(ScheduledCommand command) {
        generate.setScheduledCommand(command);
    }

    @Override
    public void setPresenter(DataPanelPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void forestStarted() {
        treebar.clearItems();
        generate.setEnabled(false);
    }

    @Override
    public void setForestStatus(int done, int total) {
        //trees.setHTML("Trees: Generated " + done + " of " + total);
    }

    @Override
    public void forestFinish(int count) {
        for (int i = 0; i < count; i++) {
            final int index = i;
            treebar.addItem(String.valueOf(i + 1), presenter.getTreeVisCommand(index));
        }
        trees.setEnabled(true);
    }

    @Override
    public void setColumns(Set<String> columns) {
        for (final String key : columns) {
            dataTable.addColumn(new TextColumn<Map<String, String>>() {
                @Override
                public String getValue(Map<String, String> row) {
                    return row.get(key);
                }
            }, key);
        }
    }

    @Override
    public void setData(List<Map<String, String>> data) {
        dataTable.setRowData(data);
    }
}
