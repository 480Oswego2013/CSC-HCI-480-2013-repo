package edu.oswego.csc480_hci521_2013.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 *
 */
public class MenuClickEvent extends GwtEvent<MenuClickEventHandler>
{
    public static Type<MenuClickEventHandler> TYPE = new Type<MenuClickEventHandler>();

    private String name;

    public MenuClickEvent(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public Type<MenuClickEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(MenuClickEventHandler h)
    {
        h.onMenuClick(this);
    }
}
