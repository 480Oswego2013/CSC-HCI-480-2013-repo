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
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import edu.oswego.csc480_hci521_2013.shared.h2o.RFRequest;
import edu.oswego.csc480_hci521_2013.shared.h2o.RFRequestImpl;

public class RFRequestView extends Composite implements Editor<RFRequestImpl> {
    
    private static RFRequestViewUiBinder uiBinder = GWT.create(RFRequestViewUiBinder.class);
    
    interface RFRequestViewUiBinder extends UiBinder<Widget, RFRequestView> {
    }
    
    private RFRequestImpl request;
    
    @UiField
    RFRequestEditor requestEditor;
    
    interface Driver extends SimpleBeanEditorDriver<RFRequestImpl, RFRequestEditor> {        
    }
    
    Driver driver = GWT.create(Driver.class);
            
    @Inject
    public RFRequestView() {
        this.request = new RFRequestImpl();
        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(requestEditor);
        driver.edit(request);
    }
    
    @UiField
    Button issueRequest;
    
    @UiHandler("issueRequest")
    void onRequest(ClickEvent e) {
        driver.flush();
    }
}