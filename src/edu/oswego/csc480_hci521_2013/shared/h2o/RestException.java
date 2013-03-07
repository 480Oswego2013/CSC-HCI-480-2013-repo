package edu.oswego.csc480_hci521_2013.shared.h2o;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public class RestException extends Exception implements IsSerializable
{

    public RestException()
    {
    }

    public RestException(String message)
    {
        super(message);
    }

    public RestException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RestException(Throwable cause)
    {
        super(cause);
    }

}
