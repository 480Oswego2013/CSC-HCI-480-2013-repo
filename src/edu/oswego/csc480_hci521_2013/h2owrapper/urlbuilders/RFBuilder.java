package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

/**
 *
 */
public class RFBuilder extends AbstractBuilder
{
    public RFBuilder(String dataKey)
    {
        super("RF.json");
        addArg("data_key", dataKey);
    }

    public RFBuilder setModelKey(String value)
    {
        addArg("model_key", value);
        return this;
    }

    /*
response_variable	Column name	The output classification (also known as 'response variable') that is being learned.
ntree	Integer from 0 to 2147483647	null
gini	use gini statistic (otherwise entropy is used)	null
class_weights	Category weight (positive)	null
stratify	Use Stratified sampling	null
strata	Category strata (integer)	null
model_key	Valid H2O key	null
out_of_bag_error_estimate	Out of bag errors	null
features	Integer from 1 to 2147483647	null
ignore	Columns to select	null
sample	Integer from 1 to 100	null
bin_limit	Integer from 0 to 65535	null
depth	Integer from 0 to 2147483647	null
seed	Integer value	null
parallel	Build trees in parallel	null
exclusive_split_limit	Integer from 0 to 2147483647	null
* */
}
