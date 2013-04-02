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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.Command;
import com.google.web.bindery.event.shared.EventBus;

import edu.oswego.csc480_hci521_2013.client.activity.VisPanelActivity;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.client.place.popout.ConfusionMatrixPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.adapters.ConfusionMatrixAdapter;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;

public class ConfusionMatrixPresenterImpl implements ConfusionMatrixPresenter {

	static final Logger logger = Logger.getLogger(ConfusionMatrixPresenterImpl.class
			.getName());
	
    RF randomForest;
    EventBus eventbus;
    ConfusionMatrixView view;
    VisPanelActivity parent;

    public ConfusionMatrixPresenterImpl(VisPanelActivity parent, ConfusionMatrixView view, EventBus eventBus, RF randomForest) {
        this.view = view;
        this.eventbus = eventBus;
        this.randomForest = randomForest;
        this.parent = parent;

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
        updateView(this.view, data);
    }
    
	public Command getPopOutCommand() {		
		return new Command() {
            @Override
            public void execute() {
            	ConfusionMatrixPlace place = new ConfusionMatrixPlace();
        		place.setDataKey(randomForest.getDataKey());
        		place.setTreeCount(randomForest.getNtree());
        		place.setIgnoreCols(new int[]{}); //TODO
        		
        		logger.log(Level.INFO, "Popping out CM panel...");
        		logger.log(Level.INFO, randomForest.getDataKey() + " "+ randomForest.getNtree());
        		parent.popOut(place, view, "Confusion Matrix");
            }
        };
	}

	public Command getCloseCommand() {
		// TODO Auto-generated method stub
		return null;
	}

    public void updateView(ConfusionMatrixView matrixView, RFView data) {
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
        
        matrixView.getPopOutItem().setScheduledCommand(getPopOutCommand());
        matrixView.getCloseItem().setScheduledCommand(getCloseCommand());
    }

    @Override
    public ConfusionMatrixView getView() {
        return view;
    }

}
