package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

/**
 *
 */
public class ImportUrlBuilder extends AbstractBuilder
{

    ImportUrlBuilder()
    {
    }

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
