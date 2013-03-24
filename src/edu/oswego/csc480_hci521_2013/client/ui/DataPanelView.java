package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.IsWidget;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public interface DataPanelView extends IsWidget {

    void setGenerateCommand(Scheduler.ScheduledCommand command);
    
    void setData(List<Map<String, String>> data);

    void setColumns(Set<String> columns);

    void setPresenter(DataPanelPresenter presenter);

    void forestStarted();

    void setForestStatus(int done, int total);

    void forestFinish(int count);
}
