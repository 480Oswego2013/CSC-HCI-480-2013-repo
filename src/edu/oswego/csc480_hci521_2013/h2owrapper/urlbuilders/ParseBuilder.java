package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

import java.net.URI;
import java.net.URL;

/**
 *
 */
public class ParseBuilder extends AbstractBuilder
{
    private String sourceKey;
    private String destinationKey;
    private boolean header = false;

    public ParseBuilder(String source_key)
    {
        this.sourceKey = source_key;
    }

    public ParseBuilder setDestinationKey(String destinationKey)
    {
        this.destinationKey = destinationKey;
        return this;
    }

    public ParseBuilder setHeader(boolean header)
    {
        this.header = header;
        return this;
    }

    @Override
    public URL build() throws Exception
    {
        String query = "source_key=" + sourceKey;
        if (destinationKey != null) {
            query += "&destination_key=" + destinationKey;
        }
        if (header) {
            query += "&header=1";
        }
        return new URI(getProtocol(), null, getHost(), getPort(), "/Parse.json", query, null).toURL();
    }
}
