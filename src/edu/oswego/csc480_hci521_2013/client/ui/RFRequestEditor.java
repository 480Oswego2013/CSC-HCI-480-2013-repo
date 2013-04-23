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
import com.google.gwt.editor.client.Editor;
import com.google.gwt.text.shared.testing.PassthroughRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import edu.oswego.csc480_hci521_2013.shared.h2o.RFRequest;

public class RFRequestEditor extends Composite implements Editor<RFRequest> {

    private static RFRequestEditorUiBinder uiBinder = GWT.create(RFRequestEditorUiBinder.class);

    interface RFRequestEditorUiBinder extends UiBinder<Widget, RFRequestEditor> {
    }
    @UiField
    TextBox dataKey;
    @UiField(provided = true)
    ValueListBox<String> classVariable = new ValueListBox<String>(PassthroughRenderer.instance(),
            new ProvidesKey<String>() {
        @Override
        public Object getKey(String item) {
            return item;
        }
    });

    public RFRequestEditor() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
