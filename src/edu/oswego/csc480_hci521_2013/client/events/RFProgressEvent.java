package edu.oswego.csc480_hci521_2013.client.events;

import com.google.gwt.event.shared.GwtEvent;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;

/**
 *
 */
public class RFProgressEvent extends GwtEvent<RFProgressEventHandler> {

    public static final Type<RFProgressEventHandler> TYPE = new Type<RFProgressEventHandler>();

    private RFView data;

    public RFProgressEvent(RFView data) {
        this.data = data;
    }

    public RFView getData() {
        return data;
    }

    @Override
    public Type<RFProgressEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RFProgressEventHandler h) {
        h.onDataUpdate(this);
    }
}
