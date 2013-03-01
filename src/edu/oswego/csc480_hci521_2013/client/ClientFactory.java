package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import edu.oswego.csc480_hci521_2013.client.ui.MenuView;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;

public interface ClientFactory {
	
  EventBus getEventBus();

  PlaceController getPlaceController();
  
  MenuView getMainView();
  PanelView getDoublePanelView();
  
}
