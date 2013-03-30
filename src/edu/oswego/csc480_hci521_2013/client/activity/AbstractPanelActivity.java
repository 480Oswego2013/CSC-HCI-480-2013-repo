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

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;

import edu.oswego.csc480_hci521_2013.client.AppPlaceHistoryMapper;
import edu.oswego.csc480_hci521_2013.client.Entry;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;

public abstract class AbstractPanelActivity extends AbstractActivity {
	
	private static final boolean DEBUG = true;
	static final Logger logger = Logger.getLogger(AbstractPanelActivity.class.getName());
	
	private final Location loc;
	private IsWidget panel;
	private String panelTitle;
	
	public abstract void addPanel(IsWidget widget, String title);
	public abstract void popPanel(IsWidget widget);
	
	public AbstractPanelActivity(Location loc) {
		this.loc = loc;
	}
	
	public AbstractPanelActivity() {
	    loc = null;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public void popout(PopoutPanelPlace place, IsWidget panel, String title) {
		logger.log(Level.INFO, "Popping panel");
		
		this.panel = panel;
		this.panelTitle = title;
		
		AppPlaceHistoryMapper historyMapper = Entry.getPlaceHistoryMapper();
		String token = historyMapper.getToken(place);
		String url = Window.Location.createUrlBuilder().setHash(token).buildString();
		int width = Window.getClientWidth()/2;
		int height = Window.getClientHeight()/2;
		String features = "width="+width+",height="+height+",menubar=0,location=0,toolbar=0,status=0";
		if(DEBUG) url = url.replaceFirst("%3A", ":");
//		Window.open(url, "_blank", features);
		
		openWindow(this, url, title, features);
		popPanel(panel);
	}
	
	public void popin() {
		logger.log(Level.INFO, "Adding panel back in!");
		addPanel(panel, panelTitle);
	}
	
	private static native void openWindow(AbstractPanelActivity parent, String url, String name, String features)/*-{
	    var window = $wnd.open(url, name, features);
		window.onbeforeunload = function() {
		    parent.@edu.oswego.csc480_hci521_2013.client.activity.AbstractPanelActivity::popin()();
		}
	}-*/;
	
}
