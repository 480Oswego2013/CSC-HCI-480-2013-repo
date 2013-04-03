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
import com.google.web.bindery.event.shared.HandlerRegistration;
import edu.oswego.csc480_hci521_2013.client.presenters.adapters.ConfusionMatrixAdapter;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixView;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import java.util.ArrayList;
import java.util.List;

public class ConfusionMatrixPresenterImpl implements ConfusionMatrixPresenter, TabPanelPresenter {

    RF randomForest;
    EventBus eventbus;
    ConfusionMatrixView view;
    RFView data;

    private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();

    public ConfusionMatrixPresenterImpl(ConfusionMatrixView view, EventBus eventBus, RF randomForest) {
        this.view = view;
        this.eventbus = eventBus;
        this.randomForest = randomForest;

        bind();
    }

    private void bind() {
        handlers.add(eventbus.addHandler(RFProgressEvent.TYPE, new RFProgressEventHandler() {
            @Override
            public void onDataUpdate(RFProgressEvent e) {
                // FIXME: the event needs to contain a reference to this view in some way so we know the requester...
                if (isOurData(e.getData())) {
                    setData(e.getData());
                }
            }
        }));
    }

    @Override
    public void added()
    {
        bind();
    }

    @Override
    public void removed()
    {
        for (HandlerRegistration h: handlers) {
            h.removeHandler();
        }
        handlers.clear();
    }

    private boolean isOurData(RFView data) {
        // TODO: this should compare the out of bag error field as well
        //       we need to get that from the confusion matrix type...
        if (data.getDataKey().equals(randomForest.getDataKey())
                && data.getModelKey().equals(randomForest.getModelKey())
                && data.getNtree() == randomForest.getNtree()
                && data.getResponseVariable() == randomForest.getResponseVariable())
        {
            return true;
        }
        return false;
    }

    public RF getRandomForest() {
        return randomForest;
    }

    @Override
    public RFView getData() {
        return data;
    }

    @Override
    public void setData(RFView data) {
        this.data = data;
        updateView(this.view, data);
    }

    public static void updateView(ConfusionMatrixView matrixView, RFView data) {
        ConfusionMatrixAdapter adapter = new ConfusionMatrixAdapter(data);
        if (data.getResponse().isPoll()) {
            matrixView.setProgress(adapter.getProgress());
        }
        else {
            matrixView.hideProgress();
        }
        matrixView.setClassificationError(adapter.getClassificationError());
        matrixView.setResponseVariable(adapter.getResponseVariable());
        matrixView.setNtree(adapter.getNtree());
        matrixView.setMtry(adapter.getMtry());
        matrixView.setRowsSkipped(adapter.getRowsSkipped());
        matrixView.setRows(adapter.getRows());
        matrixView.setMatrixType(adapter.getMatrixType());

        matrixView.setMatrixHeaders(adapter.getHeaders());
        matrixView.setMatrixScores(adapter.getScores());
        matrixView.setErrors(adapter.getErrors());
        matrixView.setTotals(adapter.getTotals());

        matrixView.setTreesGenerated(adapter.getTreesBuilt());
        matrixView.setLeavesMin(adapter.getLeavesMin());
        matrixView.setLeavesMean(adapter.getLeavesMean());
        matrixView.setLeavesMax(adapter.getLeavesMax());
        matrixView.setDepthMin(adapter.getDepthMin());
        matrixView.setDepthMean(adapter.getDepthMean());
        matrixView.setDepthMax(adapter.getDepthMax());
    }

    @Override
    public ConfusionMatrixView getView() {
        return view;
    }
}
