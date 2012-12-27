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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Block;
import java.util.function.Function;
import java.util.stream.reduce.Tabulators;

import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.impl.bag.mutable.HashBag;
import com.gs.collections.impl.list.Interval;
import com.gs.collections.impl.list.mutable.FastList;
import org.junit.Assert;
import org.junit.Test;

public class Exercise9Test extends CompanyDomainForKata
{
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
                        lineItem -> "saucer".equals(lineItem.getName())))).into(new ArrayList<Customer>());
        Assert.assertEquals("customers with saucers", 2, customersWithSaucers.size());
    }

    /**
     * Extra credit. Look into the {@link com.gs.collections.api.list.List#toMap(Function, Function)} method.
     */
    @Test
    public void ordersByCustomerUsingAsMap()
    {
        final Map<String, List<Order>> customerNameToOrders = new HashMap<>();
        Block<Customer> mapPutTransformedKeyAndValue = customer -> {customerNameToOrders.put(customer.getName(), customer.getOrders());};
        this.company.getCustomers().forEach(mapPutTransformedKeyAndValue);
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
        Map<Double, Collection<Customer>> multimap = this.company.getCustomers()
            .stream()
            .tabulate(Tabulators.<Customer, Double>groupBy((Customer customer) ->
                customer.getOrders()
                    .stream()
                    .<LineItem>flatMap((Block<? super LineItem> sink, Order element) -> {
                        element.getLineItems().forEach(sink);
                    })
                    .<Double>map(lineItem -> lineItem.getValue())
                    .reduce(0.0, (x, y) -> Math.max(x, y))));

        Assert.assertEquals(2, multimap.keySet().size());
        Assert.assertEquals(
            HashBag.newBagWith(
                    this.company.getCustomerNamed("Fred"),
                    this.company.getCustomerNamed("Bill")),
            HashBag.newBag(multimap.get(50.0)));
    }
}
