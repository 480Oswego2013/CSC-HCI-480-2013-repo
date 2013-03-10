package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;

public interface PanelView extends IsWidget {
	void buildGui();
	void addDataTab(IsWidget panel, String title);
	void addVisTab(IsWidget panel, String title);

	void setPresenter(Presenter listener);

	public interface Presenter {
		void goTo(Place place);
        void addDataTab(String key);
        void addVisTab(String datakey, String modelkey, int tree);
        void addConfusionMatrixTab(RF randomForest);
	}
}