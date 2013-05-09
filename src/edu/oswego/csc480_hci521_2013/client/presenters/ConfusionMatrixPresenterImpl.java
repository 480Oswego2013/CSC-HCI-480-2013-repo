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
package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import edu.oswego.csc480_hci521_2013.client.events.RFParameterEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFParameterEventHandler;
import edu.oswego.csc480_hci521_2013.client.presenters.adapters.ConfusionMatrixAdapter;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixView;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;

public class ConfusionMatrixPresenterImpl implements ConfusionMatrixPresenter, TabPanelPresenter {
    
    static final Logger logger = Logger.getLogger(ConfusionMatrixPresenterImpl.class.getName());
    RF randomForest;
    EventBus eventbus;
    ConfusionMatrixView view;
    RFView data;
    private String responseVariable;
    RFBuilder builder;

    private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();
    
    public ConfusionMatrixPresenterImpl(ConfusionMatrixView view, EventBus eventBus, RF randomForest, RFBuilder builder) {
        this.view = view;
        this.eventbus = eventBus;
        this.randomForest = randomForest;
        this.builder = builder;
        view.setPresenter(this);

        bind();
    }

        
    public ConfusionMatrixPresenterImpl(ConfusionMatrixView view, EventBus eventBus, RF randomForest) {
        this.view = view;
        this.eventbus = eventBus;
        this.randomForest = randomForest;
        this.builder = null;
        view.setPresenter(this);
        
        bind();
    }
    
    private void bind() {
        handlers.add(eventbus.addHandler(RFProgressEvent.TYPE, new RFProgressEventHandler() {
            @Override
            public void onDataUpdate(RFProgressEvent e) {
                if (e.getSource().equals(randomForest)) {
                    RFView rfview = e.getData();
                    logger.log(Level.INFO, rfview.toString());
                    ResponseStatus status = rfview.getResponse();
                    if (status.isPoll()) {
                        int done = status.getProgress();
                        int total = status.getProgressTotal();
                        logger.log(Level.INFO, "Trees: Generated " + done + " of " + total);
                        view.setForestStatus(done, total);
                    } else {
                        logger.log(Level.ALL, "Forest finished");
                        view.forestFinish(rfview.getNtree());
                    }
                    setData(e.getData());
                }
            }
        }));
    }
    
    @Override
    public void added() {
        bind();
    }
    
    @Override
    public void removed()
    {
        for (HandlerRegistration h : handlers) {
            h.removeHandler();
        }
        handlers.clear();
    }
    
    public RF getRandomForest() {
        return randomForest;
    }
    

    public RFBuilder getBuilder(){
        return builder;
    }

    @Override
    public RFView getData() {
        return data;
    }
    
    @Override
    public void setData(RFView data) {
        this.data = data;
        updateView(this.view, data, builder);

    }
    
    public static void updateView(ConfusionMatrixView matrixView, RFView data, RFBuilder build) {
        ConfusionMatrixAdapter adapter = new ConfusionMatrixAdapter(data, build);
        matrixView.setIdentifier(data.getDataKey() + " " + data.getModelKey());        
        if (data.getResponse().isPoll()) {
            matrixView.setProgress(adapter.getProgress());
        }
        else {
            matrixView.hideProgress();
        }
        
        matrixView.setClassificationError(adapter.getClassificationError());
        matrixView.setResponseVariable(adapter.getResponseVariable());
        matrixView.setNtree(adapter.getNtree());
        matrixView.setIgnoredUsed(build.getIgnores());
        matrixView.setClassWeightsUsed(build.getClassWeights());
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
    public ScheduledCommand getTreeVisCommand(final int index) {
        return new ScheduledCommand() {
            @Override
            public void execute() {
                eventbus.fireEvent(new TreeVisEvent(randomForest, index));
            }
        };
    }
    
    @Override
    public ConfusionMatrixView getView() {
        return view;
    }
}
