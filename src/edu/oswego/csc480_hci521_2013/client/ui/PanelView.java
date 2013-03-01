package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface PanelView extends IsWidget {
	void buildGui();
	void addDataTab(String title);
	void addVisTab(String title);

	void setPresenter(Presenter listener);

	public interface Presenter {
		void goTo(Place place);
	}
}