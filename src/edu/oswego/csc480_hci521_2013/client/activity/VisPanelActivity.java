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

package edu.oswego.csc480_hci521_2013.client.activity;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;

import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEventHandler;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.popout.ConfusionMatrixPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.presenters.VisPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixViewImpl;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

public class VisPanelActivity extends AbstractPanelActivity implements VisPanelPresenter {
	
	static final Logger logger = Logger.getLogger(VisPanelActivity.class
			.getName());
    private PlaceController placeController;
	EventBus eventBus;
	View view;
	H2OServiceAsync h2oService;
	
	// Popout specific variables
	boolean isPopout = false;
	ConfusionMatrixPlace cmPlace;
	String title;

	public VisPanelActivity(Location loc, DoublePanelPlace place, EventBus eventBus, H2OServiceAsync service, View view, PlaceController placeController) {
		super(loc);
        this.view = view;
		this.eventBus = eventBus;
		this.h2oService = service;
        this.placeController = placeController;
	}
	
	public VisPanelActivity(ConfusionMatrixPlace place, EventBus eventBus, H2OServiceAsync service, PlaceController placeController) {
		this.eventBus = eventBus;
		this.h2oService = service;
        this.placeController = placeController;
        
        isPopout = true;
        cmPlace = place;
	}
	
	
	//==========================================================================
	// Activity lifecycle methods
	//==========================================================================
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		if(isPopout) {
			RFBuilder builder = new RFBuilder(cmPlace.getDataKey());
	        builder.setNtree(cmPlace.getTreeCount());
//	        builder.setResponseVariable(classVarVal);
	        for(int i : cmPlace.getIgnoreCols())
	        	builder.setIgnore(i);
	        
	        final SimplePanel cmPanel = new SimplePanel();
	        h2oService.generateRandomForest(builder, new AsyncCallback<RF>() {
                @Override
                public void onFailure(Throwable thrwbl) {
                    logger.log(Level.SEVERE, thrwbl.toString());
                    // FIXME: do a message box or something...
                }

                @Override
                public void onSuccess(RF rf) {
                	addConfusionMatrixPanel(cmPanel, rf);
                	setTitle(generateTitle(rf));
                }
            });
	        
	        panel.setWidget(cmPanel);
	        
		} else {
			this.view.setPresenter(this);
			panel.setWidget(view);
			bind();
		}
		
//		// Add/remove any data tabs if necessary
//		if(isPopout)
////			addDataTab(dataKeys[0]);
//			System.out.println();
//		else {
//			if(visTabs != null) {
//				List<Integer> toBeAdded = new ArrayList<Integer>();
//				List<Integer> toBeRemoved = new ArrayList<Integer>();
//				for(int i=0; i<visTabs.length; i++) {
//					if(view.getTabCount()-1 < i) {
//						toBeAdded.add(i);
//						continue;
//					}
//	//				if(!dataKeys[i].equals(view.getTab(i).getDataKey())) {
//	//					toBeRemoved.add(i);
//	//					toBeAdded.add(i);
//	//				}
//				}
//	//			if(view.getTabCount() > dataKeys.length) {
//	//				int count = view.getTabCount()-dataKeys.length;
//	//				for(int i=view.getTabCount()-1; i>view.getTabCount()-1-count; i--)
//	//					toBeRemoved.add(i);
//	//			}
//	//			for(int i : toBeRemoved)
//	//				view.removeDataTab(i);
//				for(int i : toBeAdded)
//					if (visTabs[i].getType() == VisTab.Type.CM)
//						addConfusionMatrixTab(visTabs[i].getDataKey(), visTabs[i].getModelKey());
//			}
//		}
	}

	@Override
	public void addPanel(IsWidget widget, String title) {
		// TODO add popped panel back in
	}

	@Override
	public void popPanel(IsWidget widget) {
		view.removeTab(view.getActiveTabIndex());
	}
	
	//==========================================================================
	// Presenter methods
	//==========================================================================

	
	
	//==========================================================================
	// Helper methods
	//==========================================================================
	
	private void bind() {
        eventBus.addHandler(RFGenerateEvent.TYPE, new RFGenerateEventHandler() {
            @Override
            public void onStart(RFGenerateEvent e) {
            	logger.log(Level.INFO, "Adding confusion matrix...");
            	addConfusionMatrixTab(e.getData());
            }
        });
    }
	
	private void addConfusionMatrixTab(RF rf) {
        ConfusionMatrixPresenter presenter = new ConfusionMatrixPresenterImpl(this, new ConfusionMatrixViewImpl(), eventBus, rf);
//        String title = "Confusion Matrix<br>" + rf.getDataKey() + "<br>" + rf.getModelKey();
        String title = generateTitle(rf);
        view.addVisTab(presenter.getView(), title);
    }
	
	private void addConfusionMatrixPanel(AcceptsOneWidget parent, RF rf) {
        ConfusionMatrixPresenter presenter = new ConfusionMatrixPresenterImpl(this, new ConfusionMatrixViewImpl(), eventBus, rf);
        parent.setWidget(presenter.getView());
    }
	
	private String generateTitle(RF rf) {
//		return "Confusion Matrix<br>" + rf.getDataKey() + "<br>" + rf.getModelKey();
		return rf.getDataKey() + " Confusion Matrix";
	}
	
	private void setTitle(String title) {
		this.title = title;
	}
	
	public void goTo(Place place) {
        this.placeController.goTo(place);
    }

}
