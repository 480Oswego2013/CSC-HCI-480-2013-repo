package edu.oswego.csc480_hci521_2013.h2owrapper.json.objects;

/**
 *
 */
public class ColumnDef
{
    private String name;
    private int offset;
    private int size;
    private int base;
    private int scale;
    private double min;
    private double max;
    private double mean;
    private double variance;
    private int num_missing_values;
    private int enum_domain_size;

    public String getName()
    {
        return name;
    }

    public int getOffset()
    {
        return offset;
    }

    public int getSize()
    {
        return size;
    }

    public int getBase()
    {
        return base;
    }

    public int getScale()
    {
        return scale;
    }

    public double getMin()
    {
        return min;
    }

    public double getMax()
    {
        return max;
    }

    public double getMean()
    {
        return mean;
    }

    public double getVariance()
    {
        return variance;
    }

    public int getNumMissingValues()
    {
        return num_missing_values;
    }

    public int getEnumDomainSize()
    {
        return enum_domain_size;
    }

    @Override
    public String toString()
    {
        return "ColumnDef{" + "name=" + name + ", offset=" + offset + ", size=" + size + ", base=" + base + ", scale=" + scale + ", min=" + min + ", max=" + max + ", mean=" + mean + ", variance=" + variance + ", num_missing_values=" + num_missing_values + ", enum_domain_size=" + enum_domain_size + '}';
    }
}
