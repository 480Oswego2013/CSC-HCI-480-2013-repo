package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

/**
 *
 */
public class InspectBuilder extends AbstractBuilder
{

    InspectBuilder()
    {
    }

    public InspectBuilder(String key)
    {
        super("Inspect.json");
        addArg("key", key);
    }

    /**
     *
     * @param view the number of items to view, used for pagination of parsed data
     * @return
     */
    public InspectBuilder setView(Integer view)
    {
        addArg("view", view.toString());
        return this;
    }

    /**
     *
     * @param offset the offset into the data to return, used for pagination of parsed data
     * @return
     */
    public InspectBuilder setOffset(Integer offset)
    {
        addArg("offset", offset.toString());
        return this;
    }
}
