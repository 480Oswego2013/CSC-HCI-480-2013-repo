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
	
	public AbstractPanelActivity() {
	    loc = null;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public void popout(IsWidget widget, String token) {
		//Window.open("../Popout.html#Popout:datakey=cars.hex", "_blank", null);

		Window.open("../Popout.html#datakey=cars.hex", "_blank", null);
		popPanel(widget);
	}
	
	public void popin(IsWidget widget) {
		addPanel(widget);
	}
	
}
