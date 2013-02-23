package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

/**
 *
 */
public class ImportUrlBuilder extends AbstractBuilder
{
    public ImportUrlBuilder(String url)
    {
        super("ImportUrl.json");
        addArg("url", url);
    }

    public ImportUrlBuilder setKey(String key)
    {
        addArg("key", key);
        return this;
    }
}
