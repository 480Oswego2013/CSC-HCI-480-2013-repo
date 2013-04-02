package edu.oswego.csc480_hci521_2013.client.events;

import com.google.gwt.event.shared.GwtEvent;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;

/**
 *
 */
public class PopoutDataPanelEvent extends GwtEvent<PopoutDataPanelEventHandler> {

    public static Type<PopoutDataPanelEventHandler> TYPE = new Type<PopoutDataPanelEventHandler>();

    private DataPanelPresenter presenter;

    public PopoutDataPanelEvent(DataPanelPresenter presenter) {
        this.presenter = presenter;
    }

    public DataPanelPresenter getPresenter() {
        return presenter;
    }

    @Override
    public Type<PopoutDataPanelEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PopoutDataPanelEventHandler h) {
        h.onPopout(this);
    }
}
