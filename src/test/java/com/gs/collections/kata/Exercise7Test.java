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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Comparators;
import java.util.List;
import java.util.function.Block;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Double> sortedTotalValues = this.company.getCustomers()
            .stream()
            .map(Customer::getTotalOrderValue)
            .sorted(Comparators.<Double>naturalOrder())
            .collect(Collectors.<Double>toList());

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assert.assertEquals("Highest total order value", Double.valueOf(857.0), sortedTotalValues.get(sortedTotalValues.size() - 1));
        Assert.assertEquals("Lowest total order value", Double.valueOf(71.0), sortedTotalValues.stream().findFirst().get());
    }

    /**
     * Find the max total order value across all customers.
     */
    @Test
    public void maximumTotalOrderValue()
    {
        Stream<Double> values = this.company.getCustomers().stream().map(Customer::getTotalOrderValue);
        Double maximumTotalOrderValue = values.reduce(Double.valueOf(0), (left, right) -> Math.max(left, right));
        Assert.assertEquals("max value", Double.valueOf(857.0), maximumTotalOrderValue);
    }

    /**
     * Find the customer with the highest total order value.
     */
    @Test
    public void customerWithMaxTotalOrderValue()
    {
        Comparator<Customer> comparator = Comparators.<Customer>comparing(Customer::getTotalOrderValue);
        Customer customerWithMaxTotalOrderValue1 = Collections.max(this.company.getCustomers(), comparator);
        Customer mary = this.company.getCustomerNamed("Mary");
        Assert.assertEquals(mary, customerWithMaxTotalOrderValue1);
        Customer customerWithMaxTotalOrderValue2 =
            this.company.getCustomers().stream().reduce(
                null,
                (left, right) ->
                    (left != null && left.getTotalOrderValue() >= right.getTotalOrderValue()) ? left : right);
        Assert.assertEquals(mary, customerWithMaxTotalOrderValue2);
    }

    /**
     * Create some code to get the company's supplier names as a tilde delimited string.
     */
    @Test
    public void supplierNamesAsTildeDelimitedString()
    {
        String tildeSeparatedNames =
            Arrays.asList(this.company.getSuppliers())
                .stream()
                .map(Supplier::getName)
                .reduce(null, (left, right) -> (left == null ? "" : left + "~") + right);
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
            .stream()
            .filter(customer -> "London".equals(customer.getCity()))
            .explode((Stream.Downstream<Order> downstream, Customer customer) -> {
                downstream.send(customer.getOrders());
            })
            .forEach(deliver);
        Predicate<Order> isDelivered = Order::isDelivered;
        Assert.assertTrue(this.company.getCustomerNamed("Fred").getOrders().stream().allMatch(isDelivered));
        Assert.assertTrue(this.company.getCustomerNamed("Mary").getOrders().stream().allMatch(isDelivered.negate()));
        Assert.assertTrue(this.company.getCustomerNamed("Bill").getOrders().stream().allMatch(isDelivered));
    }
}
