package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public class StoreViewRow implements IsSerializable
{
    private String key;
    private int value_size_bytes;
    private String rows; // not always a number
    private String cols; // not always a number

    // i assume this is variable so it should probably be translated into an array
    // col_0 { min, mean, max }
    // col_1
    // col_2
    // col_3
    // col_4

    private String value;

    public String getKey()
    {
        return key;
    }

    public int getValueSizeBytes()
    {
        return value_size_bytes;
    }

    public String getRows()
    {
        return rows;
    }

    public String getCols()
    {
        return cols;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "StoreViewRow{" + "key=" + key + ", value_size_bytes=" + value_size_bytes + ", rows=" + rows + ", cols=" + cols + ", value=" + value + '}';
    }
}
