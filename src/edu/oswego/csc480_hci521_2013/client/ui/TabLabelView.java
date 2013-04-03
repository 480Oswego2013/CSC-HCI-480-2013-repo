package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import edu.oswego.csc480_hci521_2013.client.presenters.DoublePanelPresenter;

/**
 *
 */
public interface TabLabelView  extends IsWidget {

    void setLabel(String html);

    void setPresenter(DoublePanelPresenter presenter);
}
