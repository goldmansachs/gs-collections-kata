/*
 * Copyright 2015 Goldman Sachs.
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gs.collections.impl.bag.mutable.HashBag;
import com.gs.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class Exercise9Test extends CompanyDomainForKata
{
    /**
     * Extra credit. Aggregate the total order values by city.
     */
    @Test
    public void totalOrderValuesByCity()
    {
        Map<String, Double> map = this.company.getCustomers()
                .stream()
                .collect(groupingBy(Customer::getCity, summingDouble(Customer::getTotalOrderValue)));
        Assert.assertEquals(2, map.size());
        Assert.assertEquals(446.25, map.get("London"), 0.0);
        Assert.assertEquals(857.0, map.get("Liphook"), 0.0);
    }

    /**
     * Extra credit. Aggregate the total order values by item.
     */
    @Test
    public void totalOrderValuesByItem()
    {
        Map<String, Double> map = this.company.getOrders()
                .stream()
                .flatMap(order -> order.getLineItems().stream())
                .collect(groupingBy(LineItem::getName, summingDouble(LineItem::getValue)));
        Verify.assertSize(12, map);
        Assert.assertEquals(100.0, map.get("shed"), 0.0);
        Assert.assertEquals(10.5, map.get("cup"), 0.0);
    }

    /**
     * Extra credit. Figure out which customers ordered saucers (in any of their orders).
     */
    @Test
    public void whoOrderedSaucers()
    {
        List<Customer> customersWithSaucers =
                this.company.getCustomers().stream().filter(
                        customer -> customer.getOrders().stream().anyMatch(
                                order -> order.getLineItems().stream().anyMatch(
                                        lineItem -> "saucer".equals(lineItem.getName()))))
                        .collect(Collectors.<Customer>toList());
        Assert.assertEquals("customers with saucers", 2, customersWithSaucers.size());
    }

    /**
     * Extra credit.
     */
    @Test
    public void ordersByCustomerUsingAsMap()
    {
        final Map<String, List<Order>> customerNameToOrders = new HashMap<>();
        this.company.getCustomers().forEach(customer -> customerNameToOrders.put(customer.getName(), customer.getOrders()));
        Assert.assertNotNull("customer name to orders", customerNameToOrders);
        Assert.assertEquals("customer names", 3, customerNameToOrders.size());
        List<Order> ordersForBill = customerNameToOrders.get("Bill");
        Assert.assertEquals("Bill orders", 3, ordersForBill.size());
    }

    /**
     * Extra credit. Create a multimap where the values are customers and the key is the price of
     * the most expensive item that the customer ordered.
     */
    @Test
    public void mostExpensiveItem()
    {
        Map<Double, List<Customer>> multimap = this.company.getCustomers()
                .stream()
                .collect(groupingBy((Customer customer) ->
                        customer.getOrders()
                                .stream()
                                .flatMap((Order order) -> order.getLineItems().stream())
                                .mapToDouble(LineItem::getValue)
                                .reduce(0.0, Math::max)));

        Assert.assertEquals(2, multimap.keySet().size());
        Assert.assertEquals(
                HashBag.newBagWith(
                        this.company.getCustomerNamed("Fred"),
                        this.company.getCustomerNamed("Bill")),
                HashBag.newBag(multimap.get(50.0)));
    }
}
