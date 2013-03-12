package edu.oswego.csc480_hci521_2013.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;

import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;

public abstract class AbstractPanelActivity extends AbstractActivity {
	
	private final Location loc;
	
	
	public abstract void addPanel(IsWidget widget);
	public abstract void popPanel(IsWidget widget);
	
	
	public AbstractPanelActivity(Location loc) {
		this.loc = loc;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public void popout(IsWidget widget, Place place) {
		
		Window.open("popup.html#"+"", "_blank", null);
	}
	
	public void popin(IsWidget widget) {
		addPanel(widget);
	}
	
}
