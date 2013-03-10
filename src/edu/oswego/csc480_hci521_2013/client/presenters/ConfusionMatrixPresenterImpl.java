package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.web.bindery.event.shared.EventBus;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;

/**
 *
 */
public class ConfusionMatrixPresenterImpl implements ConfusionMatrixPresenter {
    RF randomForest;
    EventBus eventbus;
    View view;

    public ConfusionMatrixPresenterImpl(View view, EventBus eventbus, RF randomForest) {
        this.view = view;
        this.eventbus = eventbus;
        this.randomForest = randomForest;

        view.setPresenter(this);
        view.buildUi();
        bind();
    }

    private void bind() {
        eventbus.addHandler(RFProgressEvent.TYPE, new RFProgressEventHandler() {
            @Override
            public void onDataUpdate(RFProgressEvent e) {
                if (isOurData(e.getData())) {
                    setData(e.getData());
                }
            }
        });
    }

    private boolean isOurData(RFView data) {
        if (data.getDataKey().equals(randomForest.getDataKey())
                && data.getModelKey().equals(randomForest.getModelKey())) {
            return true;
        }
        return false;
    }

    @Override
    public void setData(RFView data) {
        view.setData(data);
    }
}
