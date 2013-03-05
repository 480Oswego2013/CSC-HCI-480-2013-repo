package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface PanelView extends IsWidget {
	void buildGui();
	void addDataTab(String key);
	void addVisTab(String datakey, String modelkey, int tree);

	void setPresenter(Presenter listener);

	public interface Presenter {
		void goTo(Place place);
	}
}