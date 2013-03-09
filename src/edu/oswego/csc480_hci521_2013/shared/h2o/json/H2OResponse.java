package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public abstract class H2OResponse implements IsSerializable {

    String error;
    ResponseStatus response;

    public String getError() {
        return error;
    }

    public ResponseStatus getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "H2OResponse{" + "error=" + error + ", response=" + response + '}';
    }
}
