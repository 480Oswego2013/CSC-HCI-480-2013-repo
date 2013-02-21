package edu.oswego.csc480_hci521_2013.h2owrapper.json.objects;

/**
 *
 */
public class ImportUrl
{
    private String key;
    private String url;
    private ResponseStatus response;

    public String getKey()
    {
        return key;
    }

    public String getUrl()
    {
        return url;
    }

    public ResponseStatus getResponse()
    {
        return response;
    }

    @Override
    public String toString()
    {
        return "ImportUrl{" + "key=" + key + ", url=" + url + ", response=" + response + '}';
    }
}
