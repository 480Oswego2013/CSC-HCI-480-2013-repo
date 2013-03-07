package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

/**
 *
 */
public class RFViewBuilder extends AbstractBuilder
{

    RFViewBuilder()
    {
    }

    public RFViewBuilder(String dataKey, String modelKey)
    {
        super("RFView.json");
        addArg("data_key", dataKey);
        addArg("model_key", modelKey);
    }

    public RFViewBuilder setOutOfBagErrorEstimate(boolean value)
    {
        if (value) {
            addArg("out_of_bag_error_estimate", "true");
        } else {
            addArg("out_of_bag_error_estimate", "false");
        }
        return this;
    }

    // TODO: ntree, response_variable, class_weights. it is unclear if these arguments do anything!
}
