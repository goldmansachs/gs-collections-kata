package com.gs.collections.kata;

import com.gs.collections.api.block.procedure.ObjectIntProcedure;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.block.factory.Predicates;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.utility.ArrayIterate;
import com.gs.collections.impl.utility.Iterate;

/**
 * A company has a {@link MutableList} of {@link Customer}s.  It has an array of {@link Supplier}s, and a name.
 */
public class Company
{
    private final String name;
    private final MutableList<Customer> customers = FastList.newList();

    // suppliers are array based.
    private Supplier[] suppliers = new Supplier[0];

    public Company(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void addCustomer(Customer aCustomer)
    {
        this.customers.add(aCustomer);
    }

    public MutableList<Customer> getCustomers()
    {
        return this.customers;
    }

    public MutableList<Order> getOrders()
    {
        return this.customers.flatCollect(Customer.TO_ORDERS);
    }

    public Customer getMostRecentCustomer()
    {
        return this.customers.getLast();
    }

    public void addSupplier(Supplier supplier)
    {
        // need to replace the current array of suppliers with another, larger array
        // Of course, normally one would not use an array.
        final Supplier[] currentSuppliers = this.suppliers;
        this.suppliers = new Supplier[currentSuppliers.length + 1];
        ArrayIterate.forEachWithIndex(
                currentSuppliers,
                new ObjectIntProcedure<Supplier>()
                {
                    @Override
                    public void value(Supplier eachSupplier, int index)
                    {
                        Company.this.suppliers[index] = currentSuppliers[index];
                    }
                }
        );
        this.suppliers[this.suppliers.length - 1] = supplier;
    }

    public Supplier[] getSuppliers()
    {
        return this.suppliers;
    }

    public Customer getCustomerNamed(String name)
    {
        return this.customers.detect(Predicates.attributeEqual(Customer.TO_NAME, name));
    }
}
