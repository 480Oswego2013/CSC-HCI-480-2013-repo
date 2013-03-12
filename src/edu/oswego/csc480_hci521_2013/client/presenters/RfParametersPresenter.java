package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.gwt.user.client.ui.IsWidget;


public interface RfParametersPresenter {

    public View getView();

    public interface View extends IsWidget {

        void buildUi();
        void showPopUp();
        void setPresenter(RfParametersPresenter presenter);
    }
}
