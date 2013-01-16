/*
 * Copyright 2012 Goldman Sachs.
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A company has a {@link ArrayList} of {@link Customer}s.  It has an array of {@link Supplier}s, and a name.
 */
public class Company
{
    private final String name;
    private final List<Customer> customers = new ArrayList<>();

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

    public List<Customer> getCustomers()
    {
        return this.customers;
    }

    public List<Order> getOrders()
    {
        return this.customers
            .stream().explode((Stream.Downstream<Order> downstream, Customer customer) -> {
                downstream.send(customer.getOrders());
            })
            .collect(Collectors.<Order>toList());
    }

    public Customer getMostRecentCustomer()
    {
        return this.customers.get(this.customers.size() - 1);
    }

    public void addSupplier(Supplier supplier)
    {
        // need to replace the current array of suppliers with another, larger array
        // Of course, normally one would not use an array.

        final Supplier[] currentSuppliers = this.suppliers;
        this.suppliers = new Supplier[currentSuppliers.length + 1];
        System.arraycopy(currentSuppliers, 0, this.suppliers, 0, currentSuppliers.length);
        this.suppliers[this.suppliers.length - 1] = supplier;
    }

    public Supplier[] getSuppliers()
    {
        return this.suppliers;
    }

    public Customer getCustomerNamed(final String name)
    {
        /**
         * Use a {@link Predicate} to find a {@link Customer} with the name given.
         */
        return this.getCustomers()
            .stream()
            .filter(customer -> name.equals(customer.getName()))
            .findFirst()
            .orElse((Customer) null);
    }
}
