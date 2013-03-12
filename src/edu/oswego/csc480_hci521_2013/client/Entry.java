package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import edu.oswego.csc480_hci521_2013.client.activity.mapper.EastPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.MenuActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.WestPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.MenuPlace;

public class Entry implements EntryPoint {

	private Place defaultPlace = new MenuPlace();
	
	private ClientFactory clientFactory;
	private EventBus eventBus;
	private PlaceController placeController;
	private SimplePanel northPanel, eastPanel, westPanel;

	@Override
	public void onModuleLoad() {
		init();
		
		// Create ActivityManager for north panel (menu)
		ActivityMapper northActivityMapper = new MenuActivityMapper(clientFactory);
		ActivityManager northActivityManager = new ActivityManager(northActivityMapper, eventBus);
		northActivityManager.setDisplay(northPanel);
		
		// Create ActivityManager for east panel (data)
		ActivityMapper eastActivityMapper = new EastPanelActivityMapper(clientFactory);
		ActivityManager eastActivityManager = new ActivityManager(eastActivityMapper, eventBus);
		eastActivityManager.setDisplay(eastPanel);
		
		// Create ActivityManager for west panel (visualization)
		ActivityMapper westActivityMapper = new WestPanelActivityMapper(clientFactory);
		ActivityManager westActivityManager = new ActivityManager(westActivityMapper, eventBus);
		westActivityManager.setDisplay(westPanel);
		

		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		buildGui();
		historyHandler.handleCurrentHistory();
		placeController.goTo(new DoublePanelPlace());

	}
	
	private void init() {
		// Create ClientFactory using deferred binding
		clientFactory = GWT.create(ClientFactory.class);
		eventBus = clientFactory.getEventBus();
		placeController = clientFactory.getPlaceController();
		
		northPanel = new SimplePanel();
		eastPanel = new SimplePanel();
		westPanel = new SimplePanel();
	}
	
	private  void buildGui() {
		DockLayoutPanel container = new DockLayoutPanel(Unit.PX);
		container.addEast(eastPanel, 500);
		container.addWest(westPanel, 500);
		eastPanel.add(new Label("TeSt!"));
		
		RootPanel.get().add(northPanel);
		RootPanel.get().add(eastPanel);
	}
	
	
}
