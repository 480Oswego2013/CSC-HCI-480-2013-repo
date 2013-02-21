package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

import java.net.URL;

/**
 *
 */
public class StoreViewBuilder extends AbstractBuilder
{
    @Override
    public URL build() throws Exception
    {
        return new URL(getProtocol(), getHost(), getPort(), "/StoreView.json");
    }
}
