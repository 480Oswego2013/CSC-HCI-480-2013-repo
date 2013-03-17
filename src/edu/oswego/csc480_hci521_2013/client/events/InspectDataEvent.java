package edu.oswego.csc480_hci521_2013.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 *
 */
public class InspectDataEvent extends GwtEvent<InspectDataEventHandler> {

    public static final Type<InspectDataEventHandler> TYPE = new Type<InspectDataEventHandler>();
    private String name;

    public InspectDataEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Type<InspectDataEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(InspectDataEventHandler h) {
        h.onViewData(this);
    }
}
