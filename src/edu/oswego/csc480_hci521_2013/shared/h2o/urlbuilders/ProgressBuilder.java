package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

/**
 *
 */
public class ProgressBuilder extends AbstractBuilder
{
    ProgressBuilder()
    {
    }

    public ProgressBuilder(String job, String destinationKey)
    {
        super("Progress.json");
        addArg("job", job);
        addArg("destination_key", destinationKey);
    }
}
