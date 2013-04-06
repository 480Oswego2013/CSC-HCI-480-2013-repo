package edu.oswego.csc480_hci521_2013.client.urlbuilders;

import com.google.gwt.http.client.URL;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.UrlEncoder;

/**
 *
 */
public class ClientUrlEncoder implements UrlEncoder {

    @Override
    public String encode(String encode) {
        return URL.encodeQueryString(encode);
    }
}
