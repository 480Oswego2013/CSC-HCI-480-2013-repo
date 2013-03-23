package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface DataPanelView extends IsWidget {

    void buildUi();

    void setData(List<Map<String, String>> data);

    void setPresenter(DataPanelPresenter presenter);

    void forestStarted();

    void setForestStatus(int done, int total);

    void forestFinish(int count);
}
