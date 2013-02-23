package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

/**
 *
 */
public class RFTreeViewBuilder extends AbstractBuilder
{
    public RFTreeViewBuilder(String dataKey, String modelKey)
    {
        super("RFTreeView.json");
        addArg("data_key", dataKey);
        addArg("model_key", modelKey);
    }

    public RFTreeViewBuilder setTreeNumber(int value)
    {
        if (value < 0) {
            throw new IllegalArgumentException("tree number must be positive");
        }
        addArg("tree_number", new Integer(value).toString());
        return this;
    }

    public RFTreeViewBuilder setResponseVariable(int value)
    {
        addArg("response_variable", new Integer(value).toString());
        return this;
    }
}
