package edu.oswego.csc480_hci521_2013.shared.h2o;

/**
 *
 */
public class H2OException extends RestException {

    public H2OException() {
    }

    public H2OException(String message) {
        super(message);
    }

    public H2OException(String message, Throwable cause) {
        super(message, cause);
    }

    public H2OException(Throwable cause) {
        super(cause);
    }
}
