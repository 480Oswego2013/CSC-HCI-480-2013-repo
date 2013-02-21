package edu.oswego.csc480_hci521_2013.h2owrapper.json.objects;

/**
 *
 */
public class Parse
{
    private String destination_key;
    private ResponseStatus response;

    public String getDestinationKey()
    {
        return destination_key;
    }

    public ResponseStatus getResponse()
    {
        return response;
    }

    @Override
    public String toString()
    {
        return "Parse{" + "destination_key=" + destination_key + ", response=" + response + '}';
    }
}
