package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
abstract class AbstractBuilder
{
    private static final String PROTOCOL = "http";
    private static final String HOST = "localhost";
    private static final int PORT = 54321;

    private String protocol = PROTOCOL;
    private String host = HOST;
    private int port = PORT;
    private String page;

    Map<String, String> args = new HashMap<>();

    protected AbstractBuilder(String page)
    {
        this.page = "/" + page;
    }

    protected AbstractBuilder addArg(String key, String value)
    {
        args.put(key, value);
        return this;
    }

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

    public URL build() throws Exception
    {
        String query = null;
        for (String key: args.keySet()) {
            String value = args.get(key);
            if (query == null) {
                query = key + "=" + value;
            }
            else {
                query += "&" + key + "=" + value;
            }
        }
        return new URI(protocol, null, host, port, page, query, null).toURL();
    }
}
