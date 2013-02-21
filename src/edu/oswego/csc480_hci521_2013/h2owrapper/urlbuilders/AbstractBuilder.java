package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

import java.net.URL;

/**
 *
 */
public abstract class AbstractBuilder
{
    private static final String PROTOCOL = "http";
    private static final String HOST = "localhost";
    private static final int PORT = 54321;

    private String protocol = PROTOCOL;
    private String host = HOST;
    private int port = PORT;

    public String getProtocol()
    {
        return protocol;
    }

    public AbstractBuilder setProtocol(String protocol)
    {
        this.protocol = protocol;
        return this;
    }

    public String getHost()
    {
        return host;
    }

    public AbstractBuilder setHost(String host)
    {
        this.host = host;
        return this;
    }

    public int getPort()
    {
        return port;
    }

    public AbstractBuilder setPort(int port)
    {
        this.port = port;
        return this;
    }

    abstract public URL build() throws Exception;
}
