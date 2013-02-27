package edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders;

import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.ResponseStatus;
import java.util.Map.Entry;

/**
 *
 */
public class RedirectRequestBuilder extends AbstractBuilder
{
    public RedirectRequestBuilder(ResponseStatus status)
    {
        super(status.getRedirectRequest());

        if (!status.isRedirect()) {
            throw new IllegalArgumentException("not a redirect");
        }
        for (Entry<String, String> arg: status.getRedirectRequestArgs().entrySet()) {
            addArg(arg.getKey(), arg.getValue());
        }
    }
}
