package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;

import edu.oswego.csc480_hci521_2013.client.ui.MenuView;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;

public interface ClientFactory {

    EventBus getEventBus();

    PlaceController getPlaceController();

    MenuView getMainView();

    PanelView getDoublePanelView();

    H2OServiceAsync getH2OService();

    DataPanelPresenter.View getDataPanelPresenterView();

    ConfusionMatrixPresenter.View getConfusionMatrixPresenterView();
}
