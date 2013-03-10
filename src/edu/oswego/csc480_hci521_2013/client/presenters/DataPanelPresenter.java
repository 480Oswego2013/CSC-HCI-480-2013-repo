package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.IsWidget;

/**
 *
 */
public interface DataPanelPresenter {

    Command getGenerateCommand();
    Command getTreeVisCommand(int index);

    public interface View extends IsWidget {

        void buildUi();
        void setPresenter(DataPanelPresenter presenter);
        void forestStarted();
        void setForestStatus(int done, int total);
        void forestFinish(int count);
    }
}
