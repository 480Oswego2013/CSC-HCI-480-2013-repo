package edu.oswego.csc480_hci521_2013.client.activity;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;

public class MenuActivity extends AbstractActivity implements MenuView.Presenter {

	static final Logger logger = Logger.getLogger(MenuActivity.class.getName());
	
	private ClientFactory clientFactory;
	private DoublePanelPlace place;

	public MenuActivity(DoublePanelPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		MenuView menuView = clientFactory.getMainView();
		menuView.setPresenter(this);

		containerWidget.setWidget(menuView.asWidget());
	}

	@Override
	public String mayStop() {
//		return "Please don't leave me.";
		return null;
	}


	// Presenter methods

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public Command getMenuCommand(final String value) {
		return new Command() {
			@Override
			public void execute() {
				place = ((DoublePanelPlace)clientFactory.getPlaceController().getWhere()).clone();
				place.addDataKey(value);
				goTo(place);
			}
		};
	}
}
