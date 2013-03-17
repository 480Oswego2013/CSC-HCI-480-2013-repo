package edu.oswego.csc480_hci521_2013.client.services;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFViewBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RedirectRequestFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class RFViewPoller {

    static final Logger logger = Logger.getLogger(RFViewPoller.class.getName());
    static final int DELAY = 1000;

    EventBus eventbus;
    H2OServiceAsync h2oService;
    RF randomForest;
    Timer timer;

    public RFViewPoller(EventBus eventbus, H2OServiceAsync h2oService, RF randomForest) {
        this.eventbus = eventbus;
        this.h2oService = h2oService;
        this.randomForest = randomForest;

        timer = new Timer() {
            @Override
            public void run() {
                poll();
            }
        };
    }

    public void start() {
        poll();
    }

    void poll() {
        logger.log(Level.INFO, "Polling forest generation progress");
        h2oService.getRandomForestView(
                (RFViewBuilder)RedirectRequestFactory.getRequest(randomForest.getResponse()),
                new AsyncCallback<RFView>() {
            @Override
            public void onFailure(Throwable thrwbl) {
                // FIXME: need a way of handling errors...
                logger.log(Level.SEVERE, thrwbl.toString());
            }

            @Override
            public void onSuccess(RFView rfview) {
                eventbus.fireEvent(new RFProgressEvent(rfview));
                if (rfview.getResponse().isPoll()) {
                    timer.schedule(DELAY);
                }
            }
        });
    }
}
