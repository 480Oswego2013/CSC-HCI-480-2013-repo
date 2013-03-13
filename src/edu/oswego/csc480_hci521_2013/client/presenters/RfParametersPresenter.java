package edu.oswego.csc480_hci521_2013.client.presenters;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;


public interface RfParametersPresenter {

    View getView();
    void setHeaders(List<String> headers);

    public interface View extends IsWidget {

        void buildUi();

        //Displays the popup...
        void showPopUp();

        void setHeaders(List<String> headers);

        //A list of the data column headers.
        void setPresenter(RfParametersPresenter presenter);
    }
}
