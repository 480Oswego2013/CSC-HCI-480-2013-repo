package edu.oswego.csc480_hci521_2013.client.events;

import com.google.gwt.event.shared.GwtEvent;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;
/**
 *
 */
public class RFParameterEvent extends GwtEvent<RFParameterEventHandler> {

    public static Type<RFParameterEventHandler> TYPE = new Type<RFParameterEventHandler>();

    private RFBuilder builder;

    public RFParameterEvent(RFBuilder builder) {
        this.builder = builder;
    }

    public RFBuilder getBuilder() {
        return builder;
    }

    @Override
    public Type<RFParameterEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RFParameterEventHandler h) {
        h.onParams(this);
    }
}
