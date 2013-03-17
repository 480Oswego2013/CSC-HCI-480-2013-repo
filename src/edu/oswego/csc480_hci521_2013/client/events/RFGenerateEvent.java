package edu.oswego.csc480_hci521_2013.client.events;

import com.google.gwt.event.shared.GwtEvent;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;

/**
 *
 */
public class RFGenerateEvent extends GwtEvent<RFGenerateEventHandler> {

    public static final Type<RFGenerateEventHandler> TYPE = new Type<RFGenerateEventHandler>();

    private RF data;

    public RFGenerateEvent(RF data) {
        this.data = data;
    }

    public RF getData() {
        return data;
    }

    @Override
    public Type<RFGenerateEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RFGenerateEventHandler h) {
        h.onStart(this);
    }
}
