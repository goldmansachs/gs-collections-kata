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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.functions.Block;

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
            this.company.getCustomers().filter(
                customer -> customer.getOrders().anyMatch(
                    order -> order.getLineItems().anyMatch(
                        lineItem -> "saucer".equals(lineItem.getName())))).into(new ArrayList<Customer>());
        Assert.assertEquals("customers with saucers", 2, customersWithSaucers.size());
    }

    /**
     * Extra credit. Look into the {@link com.gs.collections.api.list.List#toMap(com.gs.collections.api.block.function.Function, com.gs.collections.api.block.function.Function)} method.
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
        Map<Double, List<Customer>> multimap = this.groupBy(this.company.getCustomers(),
            customer -> Collections.max(customer.getOrders().flatMap(Order::getLineItems).map(LineItem::getValue).into(new ArrayList<Double>())));
//        Assert.assertEquals(3, multimap.size());
        Assert.assertEquals(2, multimap.keySet().size());
        Assert.assertEquals(
            Arrays.asList(
                this.company.getCustomerNamed("Fred"),
                this.company.getCustomerNamed("Bill")),
            multimap.get(50.0));
    }
}
