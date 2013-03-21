package edu.oswego.csc480_hci521_2013.shared.h2o.json;

/**
 *
 */
public abstract class AbstractResponse implements H2OResponse {

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
        return ", H2OResponse{" + "error=" + error
                + ", response=" + response + '}';
    }
}
