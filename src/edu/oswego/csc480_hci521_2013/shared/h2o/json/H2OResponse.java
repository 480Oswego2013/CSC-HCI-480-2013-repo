package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public interface H2OResponse extends IsSerializable {

    String getError();

    ResponseStatus getResponse();
}
