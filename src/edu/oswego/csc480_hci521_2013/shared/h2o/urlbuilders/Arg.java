package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public class Arg implements IsSerializable
{

        public String key;
        public String value;

        public Arg(){}

        public Arg(String key, String value)
        {
            this.key = key;
            this.value = value;
        }
}
