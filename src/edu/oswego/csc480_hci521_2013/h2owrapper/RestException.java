package edu.oswego.csc480_hci521_2013.h2owrapper;

/**
 *
 */
public class RestException extends Exception
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
