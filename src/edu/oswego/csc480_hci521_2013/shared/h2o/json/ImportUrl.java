package edu.oswego.csc480_hci521_2013.shared.h2o.json;

/**
 *
 */
public class ImportUrl extends AbstractResponse {

    private String key;
    private String url;

    private ImportUrl() {
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ImportUrl{" + "key=" + key +
                ", url=" + url + super.toString() + '}';
    }
}
