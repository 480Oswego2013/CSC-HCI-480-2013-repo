package edu.oswego.csc480_hci521_2013.shared.h2o.json;

/**
 *
 */
public class Progress extends AbstractResponse {

    private String destination_key;

    private Progress() {
    }

    public String getDestinationKey() {
        return destination_key;
    }

    @Override
    public String toString() {
        return "Progress{" + "destination_key=" + destination_key
                + super.toString() + '}';
    }
}
