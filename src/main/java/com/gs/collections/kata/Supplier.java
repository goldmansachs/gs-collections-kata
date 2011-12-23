package com.gs.collections.kata;

import com.gs.collections.api.block.function.Function;

/**
 * Suppliers have a name and an array of itemNames. Suppliers don't like lists - they prefer arrays....
 */
public class Supplier
{
    public static final Function<Supplier, String> TO_NAME =
            new Function<Supplier, String>()
            {
                @Override
                public String valueOf(Supplier supplier)
                {
                    return supplier.name;
                }
            };

    public static final Function<Supplier, Integer> TO_NUMBER_OF_ITEMS =
            new Function<Supplier, Integer>()
            {
                @Override
                public Integer valueOf(Supplier supplier)
                {
                    return supplier.itemNames.length;
                }
            };

    private final String name;
    private final String[] itemNames;

    public Supplier(String name, String[] itemNames)
    {
        this.name = name;
        this.itemNames = itemNames;
    }

    public String getName()
    {
        return this.name;
    }

    public String[] getItemNames()
    {
        return this.itemNames;
    }
}
