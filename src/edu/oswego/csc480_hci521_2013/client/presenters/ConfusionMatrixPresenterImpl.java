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

package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.web.bindery.event.shared.EventBus;
import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;

/**
 *
 */
public class ConfusionMatrixPresenterImpl implements ConfusionMatrixPresenter {

    RF randomForest;
    EventBus eventbus;
    View view;

    public ConfusionMatrixPresenterImpl(ClientFactory factory, RF randomForest) {
        this.view = factory.getConfusionMatrixPresenterView();
        this.eventbus = factory.getEventBus();
        this.randomForest = randomForest;
        
        bind();
    }

    private void bind() {
        eventbus.addHandler(RFProgressEvent.TYPE, new RFProgressEventHandler() {
            @Override
            public void onDataUpdate(RFProgressEvent e) {
                if (isOurData(e.getData())) {
                    setData(e.getData());
                }
            }
        });
    }

    private boolean isOurData(RFView data) {
        if (data.getDataKey().equals(randomForest.getDataKey())
                && data.getModelKey().equals(randomForest.getModelKey())) {
            return true;
        }
        return false;
    }

    @Override
    public void setData(RFView data) {
        UpdateView(this.view, data);
    }

    public static void UpdateView(ConfusionMatrixView matrixView, RFView data) {
        ConfusionMatrixAdapter adapter = new ConfusionMatrixAdapter(data);
        matrixView.setProgress(adapter.getProgress());
        matrixView.setNtree(adapter.getNtree());
        matrixView.setMtry(adapter.getMtry());
        matrixView.setMatrixType(adapter.getMatrixType());
    }

    @Override
    public View getView() {
        return view;
    }
}
