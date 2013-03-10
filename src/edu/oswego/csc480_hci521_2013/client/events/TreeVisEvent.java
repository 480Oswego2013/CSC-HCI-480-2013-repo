package edu.oswego.csc480_hci521_2013.client.events;

import com.google.gwt.event.shared.GwtEvent;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;

/**
 *
 */
public class TreeVisEvent extends GwtEvent<TreeVisEventHandler> {

    public static Type<TreeVisEventHandler> TYPE = new Type<TreeVisEventHandler>();

    private RF data;
    private int index;

    public TreeVisEvent(RF data, int index) {
        this.data = data;
        this.index = index;
    }

    public RF getData() {
        return data;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Type<TreeVisEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(TreeVisEventHandler h) {
        h.onViewData(this);
    }
}
