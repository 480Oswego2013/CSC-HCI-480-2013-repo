package edu.oswego.csc480_hci521_2013.client.activity;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;

public class MenuActivity extends AbstractActivity implements MenuView.Presenter {

	static final Logger logger = Logger.getLogger(MenuActivity.class.getName());
	
	private DoublePanelPlace place;
    private PlaceController places;
    private MenuView menuView;
    
	public MenuActivity(DoublePanelPlace place, PlaceController places, MenuView menuView) {
		this.place = place;
        this.places = places;
        this.menuView = menuView;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		this.menuView.setPresenter(this);

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
		this.places.goTo(place);
	}

	@Override
	public Command getMenuCommand(final String value) {
		return new Command() {
			@Override
			public void execute() {
				place = ((DoublePanelPlace)places.getWhere()).clone();
				place.addDataKey(value);
				goTo(place);
			}
		};
	}
}
