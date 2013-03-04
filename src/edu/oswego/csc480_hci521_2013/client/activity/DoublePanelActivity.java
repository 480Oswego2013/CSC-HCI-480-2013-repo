package edu.oswego.csc480_hci521_2013.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.events.MenuClickEvent;
import edu.oswego.csc480_hci521_2013.client.events.MenuClickEventHandler;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;

public class DoublePanelActivity extends AbstractActivity implements PanelView.Presenter {

	private ClientFactory clientFactory;

	public DoublePanelActivity(DoublePanelPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		PanelView panelView = clientFactory.getDoublePanelView();
		panelView.setPresenter(this);
		panelView.buildGui();

		containerWidget.setWidget(panelView.asWidget());
        eventBus.addHandler(MenuClickEvent.TYPE, new MenuClickEventHandler() {
            @Override
            public void onMenuClick(MenuClickEvent e)
            {
                clientFactory.getDoublePanelView().addDataTab(e.getName());
            }
        });
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
}
