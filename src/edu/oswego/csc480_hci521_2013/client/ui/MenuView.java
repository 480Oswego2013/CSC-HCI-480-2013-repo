package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * View interface. Extends IsWidget so a view impl can easily provide its
 * container widget.
 * 
 * @author drfibonacci
 */
public interface MenuView extends IsWidget {
	void setPresenter(Presenter listener);
	void buildGui();

	public interface Presenter {
		Command getMenuCommand();
		void goTo(Place place);
	}
}