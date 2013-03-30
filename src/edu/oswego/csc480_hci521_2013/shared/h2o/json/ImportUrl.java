package edu.oswego.csc480_hci521_2013.shared.h2o.json;

/**
 *
 */
public class ImportUrl extends H2OResponse {

    private String key;
    private String url;

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ImportUrl{" + "key=" + key + ", url=" + url + " " + super.toString() + '}';
    }
}
