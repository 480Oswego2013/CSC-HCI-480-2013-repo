package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;

import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelView;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;
import edu.oswego.csc480_hci521_2013.client.ui.BasicMenuView;
import edu.oswego.csc480_hci521_2013.client.ui.ConfusionMatrixViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;

public class ClientFactoryImpl implements ClientFactory {

    private static final EventBus eventBus = new SimpleEventBus();
    private static final PlaceController placeController = new PlaceController(eventBus);
    private static final H2OServiceAsync h2oService = GWT.create(H2OService.class);
    private static MenuView mainView;
    private static DoublePanelView doublePanelView;

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public MenuView getMainView() {
        if (mainView == null) {
            mainView = new BasicMenuView();
        }
        return mainView;
    }

    @Override
    public PanelView getDoublePanelView() {
        if (doublePanelView == null) {
            doublePanelView = new DoublePanelView();
        }
        return doublePanelView;
    }

    @Override
    public H2OServiceAsync getH2OService() {
        return h2oService;
    }

    @Override
    public DataPanelPresenter.View getDataPanelPresenterView() {
        return new DataPanelViewImpl();
    }

    @Override
    public ConfusionMatrixPresenter.View getConfusionMatrixPresenterView() {
        return new ConfusionMatrixViewImpl();
    }
}
