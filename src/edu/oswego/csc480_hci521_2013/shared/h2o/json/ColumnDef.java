/*
 * Copyright 2013 State University of New York at Oswego
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 */
public class ColumnDef implements IsSerializable
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
