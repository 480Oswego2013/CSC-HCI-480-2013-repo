package edu.oswego.csc480_hci521_2013.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.place.MenuPlace;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;

public class MenuActivity extends AbstractActivity implements MenuView.Presenter {
	
	private ClientFactory clientFactory;

	public MenuActivity(MenuPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		MenuView menuView = clientFactory.getMainView();
		menuView.setPresenter(this);
		menuView.buildGui();
		
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
	public Command getMenuCommand() {
		return new Command() {
			@Override
			public void execute() {
				System.out.println();
			}
		};
	}
}
