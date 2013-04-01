package edu.oswego.csc480_hci521_2013.client.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace.PanelType;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

public class PopoutPanelActivity extends AbstractPanelActivity implements
        DataPanelPresenter {

    static final Logger logger = Logger.getLogger(PopoutPanelActivity.class
            .getName());

    EventBus eventBus;
    View view;
    H2OServiceAsync h2oService;
    String[] dataKeys;
    boolean isPopout = false;

    public PopoutPanelActivity(PopoutPanelPlace place, EventBus eventBus, H2OService h2oService) {
        // FIXME: the view needs to be built from the place
        //        the place will tell us what the view should be...
        this.eventBus = eventBus;
        this.h2oService = h2oService;
        dataKeys = new String[]{place.getDataKey()};
        isPopout = true;
    }

    // Activity lifecycle methods
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view);
    }

    @Override
    public void popPanel(IsWidget panel) {
        view.removeDataTab(view.getActiveTabIndex());
    }

    @Override
    public void addPanel(IsWidget panel, String title) {
        view.addDataTab(title, (TabView) panel);
    }

    @Override
    public Command getCloseCommand() {
        return new Command() {
            @Override
            public void execute() {
                DoublePanelPlace place = ((DoublePanelPlace) clientFactory.getPlaceController().getWhere()).clone();
                place.removeDataKey(view.getActiveTabIndex());
                goTo(place);
            }
        };
    }

}
