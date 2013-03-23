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
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.overlay.Sigma;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;

/**
 *
 */
public class TreePanel extends Composite implements IsWidget {
    // TODO: this should have an interface...

    private static Binder uiBinder = GWT.create(Binder.class);

    interface Binder extends UiBinder<Widget, TreePanel> {
    }

    private RFTreeView data;
    private String datakey;
    private String modelkey;
    private int treeIndex;
    private String canvasId;

    @UiField
    Element canvas;

    public TreePanel(RFTreeView data, String datakey, String modelkey, int treeIndex) {
        this.data = data;
        this.datakey = datakey;
        this.modelkey = modelkey;
        this.treeIndex = treeIndex;

        canvasId = (datakey + "__" + modelkey + "__" + treeIndex)
            .replaceAll("\\.", "_");

        initWidget(uiBinder.createAndBindUi(this));

        canvas.setId(canvasId);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        // NOTE: we have to wait until our canvas is added to the page
        //       before we can call javascript to render the graph in it.
        Sigma.callSigma(canvasId, data.getTree().toJson(), data.getDepth(), data.getLeaves());
    }
}
