package edu.oswego.csc480_hci521_2013.h2owrapper.json.objects;

import java.util.Map;

/**
 *
 */
public class InspectRow
{
    private int row;
    private Map<String, String> data;

    public InspectRow(int row, Map<String, String> data)
    {
        this.row = row;
        this.data = data;
    }

    public int getRow()
    {
        return row;
    }

    public String getData(String name)
    {
        return data.get(name);
    }

    @Override
    public String toString()
    {
        return "InspectRow{" + "row=" + row + ", data=" + data + '}';
    }
}
