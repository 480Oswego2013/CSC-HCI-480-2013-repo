package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
abstract class AbstractBuilder implements IsSerializable, H2ORequest
{
    private static final String PROTOCOL = "http";
    private static final String HOST = "localhost";
    private static final int PORT = 54321;

    private String protocol = PROTOCOL;
    private String host = HOST;
    private int port = PORT;
    private String page;

    private Map<String, String> args = new HashMap<String, String>();
    private List<Arg> multiargs = new ArrayList<Arg>();

    protected AbstractBuilder()
    {
    }

    protected AbstractBuilder(String page)
    {
        this.page = "/" + page;
    }

    protected AbstractBuilder addArg(String key, String value)
    {
        args.put(key, value);
        return this;
    }

    protected AbstractBuilder addMultiArg(String key, String value)
    {
        multiargs.add(new Arg(key, value));
        return this;
    }

    public AbstractBuilder setProtocol(String protocol)
    {
        this.protocol = protocol;
        return this;
    }

    public AbstractBuilder setHost(String host)
    {
        this.host = host;
        return this;
    }

    public AbstractBuilder setPort(int port)
    {
        this.port = port;
        return this;
    }

    public String build()
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
        for (Arg arg: multiargs) {
            String key = arg.key;
            String value = arg.value;
            if (query == null) {
                query = key + "=" + value;
            }
            else {
                query += "&" + key + "=" + value;
            }
        }
        String url = protocol + "://" + host + ":" + port + page;
        if (query != null) {
            url += "?" + query;
        }
        return url;
    }

    private static class Arg implements IsSerializable
    {
        String key;
        String value;

        public Arg(String key, String value)
        {
            this.key = key;
            this.value = value;
        }
    }
}
