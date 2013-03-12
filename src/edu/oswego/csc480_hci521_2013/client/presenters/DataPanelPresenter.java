package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.IsWidget;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface DataPanelPresenter {

    Command getGenerateCommand();

    Command getTreeVisCommand(int index);

    View getView();

    public interface View extends IsWidget {

        void buildUi();

        void setData(List<Map<String, String>> data);

        void setPresenter(DataPanelPresenter presenter);

        void forestStarted();

        void setForestStatus(int done, int total);

        void forestFinish(int count);
    }
}
