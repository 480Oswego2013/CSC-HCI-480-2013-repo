package edu.oswego.csc480_hci521_2013.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;

import edu.oswego.csc480_hci521_2013.client.place.PopoutDataPanelPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelViewImpl;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class PopoutDataPanelActivity extends AbstractActivity {

    static final Logger logger = Logger.getLogger(PopoutDataPanelActivity.class
            .getName());

    H2OServiceAsync h2oService;
    String datakey;

    public PopoutDataPanelActivity(PopoutDataPanelPlace place, H2OServiceAsync h2oService) {
        this.h2oService = h2oService;
        datakey = place.getDataKey();
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        h2oService.getParsedData(datakey, new AsyncCallback<List<Map<String, String>>>() {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.INFO, "Failure adding data tab.");
                logger.log(Level.SEVERE, caught.toString());
                // FIXME: do a message box or something...
            }

            @Override
            public void onSuccess(List<Map<String, String>> result)
            {
                logger.log(Level.INFO, "Building data tab.");

                DataPanelPresenter presenter = new DataPanelPresenterImpl(
                        h2oService, new DataPanelViewImpl(),
                        eventBus, datakey, result);
                panel.setWidget(presenter.getView());
            }
        });
    }
}
