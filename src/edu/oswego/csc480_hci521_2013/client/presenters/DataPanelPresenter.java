package edu.oswego.csc480_hci521_2013.client.presenters;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.IsWidget;

/**
 *
 */
public interface DataPanelPresenter {

    Command getGenerateCommand();
    Command getTreeVisCommand(int index);
    Command getPopOutCommand();
    Command getCloseCommand();
    boolean isPopout();

    public interface View extends IsWidget {
        void buildUi();
        void setPresenter(DataPanelPresenter presenter);
        void addDataTab(String title, List<Map<String, String>> data);
        void addDataTab(String title, TabView tab);
        void removeDataTab(int index);
        void clear();
        int getTabCount();
        int getActiveTabIndex();
        TabView getTab(int index);
    }
    
    public interface TabView extends IsWidget {
    	void buildUi();
        void setPresenter(DataPanelPresenter presenter);
        void forestStarted();
        void setForestStatus(int done, int total);
        void forestFinish(int count);
        String getDataKey();
    }
}
