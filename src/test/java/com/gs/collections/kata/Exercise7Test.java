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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Comparators;
import java.util.List;
import java.util.functions.Block;
import java.util.functions.Predicates;

import org.junit.Assert;
import org.junit.Test;

public class Exercise7Test extends CompanyDomainForKata
{
    /**
     * Get a list of the customers' total order values, sorted. Check out the implementation of {@link
     * Customer#getTotalOrderValue()} and {@link Order#getValue()} .
     */
    @Test
    public void sortedTotalOrderValue()
    {
        List<Double> totalValues = this.company.getCustomers().map(Customer::getTotalOrderValue).into(new ArrayList<Double>());
        List<Double> sortedTotalValues = totalValues.sorted(Comparators.<Double>naturalOrder()).into(new ArrayList<Double>());

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assert.assertEquals("Highest total order value", Double.valueOf(857.0), sortedTotalValues.get(sortedTotalValues.size() - 1));
        Assert.assertEquals("Lowest total order value", Double.valueOf(71.0), sortedTotalValues.getFirst());
    }

    /**
     * Find the max total order value across all customers.
     */
    @Test
    public void maximumTotalOrderValue()
    {
        Double maximumTotalOrderValue = Collections.max(this.company.getCustomers().map(Customer::getTotalOrderValue).into(new ArrayList<Double>()));
        Assert.assertEquals("max value", Double.valueOf(857.0), maximumTotalOrderValue);
    }

    /**
     * Find the customer with the highest total order value.
     */
    @Test
    public void customerWithMaxTotalOrderValue()
    {
        Comparator<Customer> comparator = Comparators.<Customer>comparing(Customer::getTotalOrderValue);
        Customer customerWithMaxTotalOrderValue = Collections.max(this.company.getCustomers(), comparator);
        Assert.assertEquals(this.company.getCustomerNamed("Mary"), customerWithMaxTotalOrderValue);
    }

    /**
     * Create some code to get the company's supplier names as a tilde delimited string.
     */
    @Test
    public void supplierNamesAsTildeDelimitedString()
    {
        List<String> names = Arrays.asList(this.company.getSuppliers()).map(Supplier::getName).into(new ArrayList<String>());
        final StringBuilder makeString = new StringBuilder();
        Block<String> block = each -> {makeString.append(each).append("~");};
        names.forEach(block);
        String tildeSeparatedNames = makeString.substring(0, makeString.length() - 1);
        Assert.assertEquals(
            "tilde separated names",
            "Shedtastic~Splendid Crocks~Annoying Pets~Gnomes 'R' Us~Furniture Hamlet~SFD~Doxins",
            tildeSeparatedNames);
    }

    /**
     * Deliver all orders going to customers from London.
     *
     * @see Order#deliver()
     */
    @Test
    public void deliverOrdersToLondon()
    {
        Block<Order> deliver = Order::deliver;
        this.company.getCustomers()
                .filter(customer -> "London".equals(customer.getCity()))
                .flatMap(Customer::getOrders)
                .forEach(deliver);
        Assert.assertTrue(this.company.getCustomerNamed("Fred").getOrders().allMatch(Order::isDelivered));
        Assert.assertTrue(this.company.getCustomerNamed("Mary").getOrders().allMatch(Predicates.<Order>negate((Order::isDelivered))));
        Assert.assertTrue(this.company.getCustomerNamed("Bill").getOrders().allMatch(Order::isDelivered));
    }
}
