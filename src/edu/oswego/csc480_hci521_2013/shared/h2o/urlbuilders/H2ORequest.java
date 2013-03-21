package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public interface H2ORequest extends IsSerializable {

    String build(UrlEncoder encoder);
}
