package edu.oswego.csc480_hci521_2013.server;

import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.UrlEncoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ServerUrlEncoder implements UrlEncoder {

    @Override
    public String encode(String encode) {
        try {
            return URLEncoder.encode(encode, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServerUrlEncoder.class.getName())
                    .log(Level.SEVERE, ex.getMessage(), ex);
        }
        return encode;
    }
}
