package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

/**
 *
 */
public class ProgressBuilder extends AbstractBuilder
{
    public ProgressBuilder(String job, String destinationKey)
    {
        super("Progress.json");
        addArg("job", job);
        addArg("destination_key", destinationKey);
    }
}
