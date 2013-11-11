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

import com.gs.collections.api.bag.MutableBag;
import com.gs.collections.api.bag.sorted.MutableSortedBag;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.function.primitive.DoubleToObjectFunction;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.map.MutableMap;
import com.gs.collections.api.multimap.list.MutableListMultimap;
import com.gs.collections.impl.bag.mutable.HashBag;
import com.gs.collections.impl.bag.mutable.sorted.TreeBag;
import com.gs.collections.impl.block.factory.primitive.DoublePredicates;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class Exercise9Test extends CompanyDomainForKata
{
    /**
     * Extra credit. Aggregate the total order values by city.  Hint: Look at RichIterable.aggregateBy.
     */
    @Test
    public void totalOrderValuesByCity()
    {
        MutableMap<String, Double> map =
                this.company
                    .getCustomers()
                    .aggregateBy(Customer::getCity, () -> 0.0, (result, customer) -> result + customer.getTotalOrderValue());
        Assert.assertEquals(2, map.size());
        Assert.assertEquals(446.25, map.get("London"), 0.0);
        Assert.assertEquals(857.0, map.get("Liphook"), 0.0);
    }

    /**
     * Extra credit. Aggregate the total order values by item.  Hint: Look at RichIterable.aggregateBy and remember
     * how to use flatCollect to get an iterable of all items.
     */
    @Test
    public void totalOrderValuesByItem()
    {
        MutableMap<String, Double> map =
                this.company
                    .getOrders()
                    .flatCollect(Order::getLineItems)
                    .aggregateBy(LineItem::getName, () -> 0.0, (result, lineItem) -> result + lineItem.getValue());
        Verify.assertSize(12, map);
        Assert.assertEquals(100.0, map.get("shed"), 0.0);
        Assert.assertEquals(10.5, map.get("cup"), 0.0);
    }

    /**
     * Extra credit. Find all customers' line item values greater than 7.5 and sort them by highest to lowest price.
     */
    @Test
    public void sortedOrders()
    {
        MutableSortedBag<Double> orderedPrices =
                this.company.getOrders()
                        .asLazy()
                        .flatCollect(Order::getLineItems)
                        .collectDouble(LineItem::getValue)
                        .select(value -> value > 7.5)
                        .collect(Double::valueOf)
                        .into(TreeBag.<Double>newBag(Collections.reverseOrder()));

        MutableSortedBag<Double> expectedPrices = TreeBag.newBagWith(
                Collections.reverseOrder(), 500.0, 150.0, 120.0, 75.0, 50.0, 50.0, 12.5);
        Verify.assertSortedBagsEqual(expectedPrices, orderedPrices);
    }

    /**
     * Extra credit. Figure out which customers ordered saucers (in any of their orders).
     */
    @Test
    public void whoOrderedSaucers()
    {
        MutableList<Customer> customersWithSaucers =
            this.company.getCustomers().select(
                customer -> customer.getOrders().anySatisfy(
                    order -> order.getLineItems().anySatisfy(
                        lineItem -> "saucer".equals(lineItem.getName()))));
        Verify.assertSize("customers with saucers", 2, customersWithSaucers);
    }

    /**
     * Extra credit. Look into the {@link MutableList#toMap(Function, Function)} method.
     */
    @Test
    public void ordersByCustomerUsingAsMap()
    {
        MutableMap<String, MutableList<Order>> customerNameToOrders =
            this.company.getCustomers().toMap(Customer::getName, Customer::getOrders);

        Assert.assertNotNull("customer name to orders", customerNameToOrders);
        Verify.assertSize("customer names", 3, customerNameToOrders);
        MutableList<Order> ordersForBill = customerNameToOrders.get("Bill");
        Verify.assertSize("Bill orders", 3, ordersForBill);
    }

    /**
     * Extra credit. Create a multimap where the values are customers and the key is the price of
     * the most expensive item that the customer ordered.
     */
    @Test
    public void mostExpensiveItem()
    {
        MutableListMultimap<Double, Customer> multimap = this.company.getCustomers()
            .groupBy(customer -> customer.getOrders()
                .asLazy()
                .flatCollect(Order::getLineItems)
                .collectDouble(LineItem::getValue)
                .max());
        Assert.assertEquals(3, multimap.size());
        Assert.assertEquals(2, multimap.keysView().size());
        Assert.assertEquals(
            FastList.newListWith(
                this.company.getCustomerNamed("Fred"),
                this.company.getCustomerNamed("Bill")),
            multimap.get(50.0));
    }
}
