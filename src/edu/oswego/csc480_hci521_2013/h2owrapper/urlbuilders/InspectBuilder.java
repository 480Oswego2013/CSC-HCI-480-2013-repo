package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

/**
 *
 */
public class InspectBuilder extends AbstractBuilder
{
    public InspectBuilder(String key)
    {
        super("Inspect.json");
        addArg("key", key);
    }
}
