package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

/**
 *
 */
public class StoreViewBuilder extends AbstractBuilder
{
    public StoreViewBuilder()
    {
        super("StoreView.json");
    }

    /**
     *
     * @param view the number of items to view, used for pagination of data
     * @return
     */
    public StoreViewBuilder setView(Integer view)
    {
        addArg("view", view.toString());
        return this;
    }

    /**
     *
     * @param offset the offset into the data to return, used for pagination of data
     * @return
     */
    public StoreViewBuilder setOffset(Integer offset)
    {
        addArg("offset", offset.toString());
        return this;
    }

    /**
     *
     * @param value a filter for what is returned, a simple string match is done
     * @return
     */
    public StoreViewBuilder setFilter(String value)
    {
        addArg("filter", value);
        return this;
    }
}
