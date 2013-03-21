package edu.oswego.csc480_hci521_2013.shared.h2o.json;

/**
 *
 */
public class Parse extends AbstractResponse {

    private String destination_key;

    private Parse() {
    }

    public String getDestinationKey() {
        return destination_key;
    }

    @Override
    public String toString() {
        return "Parse{" + "destination_key=" + destination_key
                + super.toString() + '}';
    }
}
