/*
 * Copyright 2011 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.kata;

import com.gs.collections.api.block.procedure.ObjectIntProcedure;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.utility.ArrayIterate;
import org.junit.Assert;

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
        Assert.fail("Refactor this code to use GS Collections as part of Exercise 4");
        MutableList<Order> orders = FastList.newList();
        for (Customer customer : this.customers)
        {
            orders.addAll(customer.getOrders());
        }
        return orders;
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
        /**
         * Use a {@link Discriminator} to find a {@link Customer} with the name given.
         */
        Assert.fail("Implement this method as part of Exercise 3");
        return null;
    }
}
