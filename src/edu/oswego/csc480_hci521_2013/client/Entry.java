package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

import edu.oswego.csc480_hci521_2013.client.activity.mapper.EastPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.MenuActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.PopoutPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.WestPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;

public class Entry implements EntryPoint {

	private final AppGinjector injector = GWT.create(AppGinjector.class);
    private static AppPlaceHistoryMapper historyMapper;
	private static final Place defaultPlace = new DoublePanelPlace();
	
	private EventBus eventBus;
	private PlaceController placeController;
	private SimplePanel northPanel, eastPanel, westPanel, popoutPanel;

	@Override
	public void onModuleLoad() {
		init();
		
		// Create ActivityManager for north panel (menu)
		ActivityMapper northActivityMapper = injector.getMenuActivityMapper();
		ActivityManager northActivityManager = new ActivityManager(northActivityMapper, eventBus);
		northActivityManager.setDisplay(northPanel);
		
		// Create ActivityManager for west panel (data)
		ActivityMapper westActivityMapper = injector.getWestPanelActivityMapper();
		ActivityManager westActivityManager = new ActivityManager(westActivityMapper, eventBus);
		westActivityManager.setDisplay(westPanel);
		
		// Create ActivityManager for east panel (visualization)
		ActivityMapper eastActivityMapper = injector.getEastPanelActivityMapper();
		ActivityManager eastActivityManager = new ActivityManager(eastActivityMapper, eventBus);
		eastActivityManager.setDisplay(eastPanel);
		
		// Create ActivityManager for popout panel (only visible with specific place)
		ActivityMapper popoutActivityMapper = injector.getPopoutPanelActivityMapper();
		ActivityManager popoutActivityManager = new ActivityManager(popoutActivityMapper, eventBus);
		popoutActivityManager.setDisplay(popoutPanel);
		

		historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		buildGui();
		historyHandler.handleCurrentHistory();
//		placeController.goTo(defaultPlace);

	}
	
	public static AppPlaceHistoryMapper getPlaceHistoryMapper() {
		return historyMapper;
	}
	
	private void init() {
		// Create ClientFactory using deferred binding
		
		eventBus = this.injector.getEventBus();
		placeController = new PlaceController(eventBus);
		
		northPanel = new SimplePanel();
		eastPanel = new SimplePanel();
		westPanel = new SimplePanel();
		popoutPanel = new SimplePanel();
	}
	
	private  void buildGui() {
		HorizontalPanel contents = new HorizontalPanel();
		contents.add(popoutPanel);
		contents.add(westPanel);
		contents.add(eastPanel);
		contents.setCellWidth(westPanel, "50%");
		contents.setCellWidth(eastPanel, "50%");
		contents.setWidth("100%");
		
		HeaderPanel container = new HeaderPanel();
		container.setHeaderWidget(northPanel);
		container.setContentWidget(contents);;
		
		RootLayoutPanel.get().add(container);
	}
	
	
}
