package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import edu.oswego.csc480_hci521_2013.client.activity.mapper.PopoutPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;

public class PopoutEntry implements EntryPoint {
	
	private ClientFactory clientFactory;
	private EventBus eventBus;
	private PlaceController placeController;
	private SimplePanel panel;
	private Place defaultPlace;

	@Override
	public void onModuleLoad() {
		init();

		ActivityMapper northActivityMapper = new PopoutPanelActivityMapper(clientFactory);
		ActivityManager northActivityManager = new ActivityManager(northActivityMapper, eventBus);
		northActivityManager.setDisplay(panel);
		
		
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		buildGui();
		historyHandler.handleCurrentHistory();
		placeController.goTo(defaultPlace);

	}
	
	private void init() {
	    defaultPlace = new PopoutPanelPlace();
		clientFactory = new ClientFactoryImpl();
		eventBus = clientFactory.getEventBus();
		placeController = clientFactory.getPlaceController();
		
		panel = new SimplePanel();
	}
	
	private  void buildGui() {
		RootPanel.get().add(panel);
	}
	

}
