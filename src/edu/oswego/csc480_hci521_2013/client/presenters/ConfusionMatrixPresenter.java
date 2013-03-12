package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.gwt.user.client.ui.IsWidget;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;

/**
 *
 */
public interface ConfusionMatrixPresenter {

    void setData(RFView data);

    View getView();

    public interface View extends IsWidget {

        void buildUi();

        void setData(RFView data);

        void setPresenter(ConfusionMatrixPresenter presenter);
    }
}
