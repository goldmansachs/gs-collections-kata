package com.gs.collections.kata;

import com.gs.collections.api.block.function.Function;

/**
 * An Item has a name and a value.
 */
public class LineItem
{
    public static final Function<LineItem, String> TO_NAME = new Function<LineItem, String>()
    {
        @Override
        public String valueOf(LineItem lineItem)
        {
            return lineItem.name;
        }
    };

    private String name;
    private final double value;

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getValue()
    {
        return this.value;
    }

    public LineItem(String name, double value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return this.name + " $ " + this.getValue();
    }
}
