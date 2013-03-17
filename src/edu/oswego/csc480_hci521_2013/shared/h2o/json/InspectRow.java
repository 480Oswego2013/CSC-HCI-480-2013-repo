package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Map;

/**
 *
 */
public class InspectRow implements IsSerializable {

    private int row;
    private Map<String, String> data;

    private InspectRow() {
    }

    public InspectRow(int row, Map<String, String> data) {
        this.row = row;
        this.data = data;
    }

    public int getRow() {
        return row;
    }

    public String getData(String name) {
        return data.get(name);
    }

    @Override
    public String toString() {
        return "InspectRow{" + "row=" + row + ", data=" + data + '}';
    }
}
