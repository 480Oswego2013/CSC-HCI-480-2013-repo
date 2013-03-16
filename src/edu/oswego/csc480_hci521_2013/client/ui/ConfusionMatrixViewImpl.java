// Copyright 2013 Oswego University 
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
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;

public class ConfusionMatrixViewImpl extends ConfusionMatrixPresenter.View {

    interface Binder extends UiBinder<Widget, ConfusionMatrixViewImpl> {
    }
    private static Binder uiBinder = GWT.create(Binder.class);

    interface Style extends CssResource {

        String tableHeader();

        String cfmatrixHighlight();
    }
    @UiField ConfusionMatrixViewImpl.Style style;
    @UiField Element progress;
    @UiField FlexTable matrixTable;
    @UiField Grid treesTable;
    @UiField Element treesGenerated;
    @UiField Element type;
    @UiField Element responseVariable;
    @UiField Element ntree;
    @UiField Element mtry;
    @UiField Element rows;
    @UiField Element classificationError;

    @Override public Element getProgress() { return this.progress; }
    @Override public Element getNtree() { return this.ntree; }
    @Override public Element getMtry() { return this.mtry; }
    @Override public Element getMatrixType() { return this.type; }

    public ConfusionMatrixViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
