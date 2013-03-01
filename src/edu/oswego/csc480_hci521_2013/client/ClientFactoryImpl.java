package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelView;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;
import edu.oswego.csc480_hci521_2013.client.ui.BasicMenuView;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;

public class ClientFactoryImpl implements ClientFactory {

	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	
	private static MenuView mainView;
	private static DoublePanelView  doublePanelView;

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
	
	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public MenuView getMainView() {
		return mainView != null ? mainView : new BasicMenuView();
	}
	
	@Override
	public PanelView getDoublePanelView() {
		return doublePanelView != null ? doublePanelView : new DoublePanelView(); 
	}

}
