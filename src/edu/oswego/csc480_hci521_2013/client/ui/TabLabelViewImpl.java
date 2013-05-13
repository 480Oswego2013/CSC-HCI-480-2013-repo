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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.presenters.DoublePanelPresenter;

/**
 *
 */
public class TabLabelViewImpl extends Composite implements TabLabelView {

    private static Binder uiBinder = GWT.create(Binder.class);

    interface Binder extends UiBinder<Widget, TabLabelViewImpl> {
    }

    private DoublePanelPresenter presenter;

    @UiField
    Element label;
    @UiField
    PushButton pop;

    public TabLabelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setLabel(String html) {
        label.setInnerHTML(html);
    }

    @Override
    public void setPresenter(DoublePanelPresenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("pop")
    void handleClick(ClickEvent e) {
        if (presenter != null) {
            presenter.popout(this);
        }
    }
    
    @UiHandler("close")
    void handleClickClose(ClickEvent e) {
        if (presenter != null) {
            presenter.close(this);
        }
    }
}
