package com.gs.collections.kata;

import com.gs.collections.api.block.function.Function;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.utility.ArrayIterate;

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

    public static final Function<Supplier, Iterable<String>> TO_ITEM_NAMES = new Function<Supplier, Iterable<String>>()
    {
        @Override
        public Iterable<String> valueOf(Supplier supplier)
        {
            return FastList.newListWith(supplier.getItemNames());
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

    public boolean supplies(String name)
    {
        return ArrayIterate.contains(this.itemNames, name);
    }
}
