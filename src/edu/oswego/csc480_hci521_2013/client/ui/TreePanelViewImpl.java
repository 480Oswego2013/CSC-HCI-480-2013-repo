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
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.overlay.Sigma;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;

/**
 *
 */
public class TreePanelViewImpl extends Composite implements TreePanelView {

    private static Binder uiBinder = GWT.create(Binder.class);

    interface Binder extends UiBinder<Widget, TreePanelViewImpl> {
    }

    private RFTreeView data;
    private String datakey;
    private String modelkey;
    private int treeIndex;
    private String canvasId;

    @UiField
    PushButton popin;
    @UiField
    HorizontalPanel popinPanel;
    @UiField
    Element canvas;
    @UiField
    Element panel;

    public TreePanelViewImpl(RFTreeView data, String datakey, String modelkey, int treeIndex) {
        this(data, datakey, modelkey, treeIndex, false);
    }
    
    public TreePanelViewImpl(RFTreeView data, String datakey, String modelkey, int treeIndex, boolean showPopinButton) {
        this.data = data;
        this.datakey = datakey;
        this.modelkey = modelkey;
        this.treeIndex = treeIndex;

        canvasId = (datakey + "__" + modelkey + "__" + treeIndex)
            .replaceAll("\\.", "_");

        initWidget(uiBinder.createAndBindUi(this));

        canvas.setId(canvasId);
        
        popinPanel.setVisible(showPopinButton);
    }

    @Override
    protected void onLoad() {
        // NOTE: we have to wait until our canvas is added to the page
        //       before we can call javascript to render the graph in it.
        //       the timer to delay rendering is needed to force an update
        //       of the canvas, normal dom manipulation does not seem to work.
        new Timer() {
            @Override
            public void run() {
                Sigma.callSigma(canvasId, data.getTree().toJson(),
                        data.getDepth(), data.getLeaves());
            }
        }.schedule(1);
    }
    
    @UiHandler("popin")
    void handleClick(ClickEvent e) {
    	closeWindow();
    }
    
    public static native void closeWindow()/*-{
    	$wnd.close();
	}-*/;
}
