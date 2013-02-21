package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

import java.net.URI;
import java.net.URL;

/**
 *
 */
public class ImportUrlBuilder extends AbstractBuilder
{
    private String url;
    private String key;

    public ImportUrlBuilder(String url)
    {
        this.url = url;
    }

    public ImportUrlBuilder setKey(String key)
    {
        this.key = key;
        return this;
    }

    @Override
    public URL build() throws Exception
    {
        String query = "url=" + url;
        if (key != null) {
            query += "&key=" + key;
        }
        return new URI(getProtocol(), null, getHost(), getPort(), "/ImportUrl.json", query, null).toURL();
    }
}
