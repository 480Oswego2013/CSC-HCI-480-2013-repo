/*
 * Copyright 2013 State University of New York at Oswego
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import java.util.logging.Logger;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView.ConfusionMatrix;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;

public class ConfusionMatrixViewImpl extends AbstractConfusionMatrix {

    private static Binder uiBinder = GWT.create(Binder.class);
    private static final Logger logger = Logger.getLogger(ConfusionMatrixViewImpl.class.getName());

    interface Binder extends UiBinder<Widget, ConfusionMatrixViewImpl> {
    }
    private ConfusionMatrixPresenter presenter;

    @UiField
    ConfusionMatrixView.Style style;

    @UiField
    PushButton popin;
    @UiField
    HorizontalPanel popinPanel;
    @UiField
    Element matrixIdentifier;
    @UiField
    Element progress;
    @UiField
    FlexTable matrixTable;
    @UiField
    Element type;
    @UiField
    Element responseVariable;
    @UiField
    Element ntree;
    @UiField
    Element mtry;
    @UiField
    Element rowsSkipped;
    @UiField
    Element rows;
    @UiField
    Element classificationError;

    @UiField
    Element treesGenerated;
    @UiField
    Grid treesTable;
    @UiField
    Element leavesMin, leavesMean, leavesMax;
    @UiField
    Element depthMin, depthMean, depthMax;

    @UiField
    MenuBar visbar;
    @UiField
    MenuBar treebar;
    @UiField
    MenuItem trees;

    public ConfusionMatrixViewImpl() {
        this(false);
    }
    
    public ConfusionMatrixViewImpl(boolean showPopinButton) {
    	initWidget(uiBinder.createAndBindUi(this));
    	popinPanel.setVisible(showPopinButton);
    }

    @Override
    public Style getStyle() {
        return style;
    }
    
    @Override
    public Element getIdentifier() {
        return this.matrixIdentifier;
    }
    
    @Override
    public Element getProgress() {
        return this.progress;
    }

    @Override
    public Element getClassificationError() {
        return classificationError;
    }

    @Override
    public Element getResponseVariable() {
        return responseVariable;
    }

    @Override
    public Element getNtree() {
        return this.ntree;
    }

    @Override
    public Element getMtry() {
        return this.mtry;
    }

    @Override
    public Element getRowsSkipped() {
        return rowsSkipped;
    }

    @Override
    public Element getRows() {
        return rows;
    }

    @Override
    public Element getMatrixType() {
        return this.type;
    }

    @Override
    public FlexTable getMatrixTable() {
        return this.matrixTable;
    }

    @Override
    public Element getTreesGenerated() {
        return treesGenerated;
    }

    @Override
    public Element getLeavesMin() {
        return this.leavesMin;
    }

    @Override
    public Element getLeavesMean() {
        return this.leavesMean;
    }

    @Override
    public Element getLeavesMax() {
        return this.leavesMax;
    }

    @Override
    public Element getDepthMin() {
        return this.depthMin;
    }

    @Override
    public Element getDepthMean() {
        return this.depthMean;
    }

    @Override
    public Element getDepthMax() {
        return this.depthMax;
    }
    
    @Override
    public void setPresenter(ConfusionMatrixPresenter presenter) {
        this.presenter = presenter;
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
    
    @UiHandler("popin")
    void handleClick(ClickEvent e) {
    	closeWindow();
    }
    
    public static native void closeWindow()/*-{
    	$wnd.close();
	}-*/;
}
