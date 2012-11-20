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

import java.util.*;
import java.util.functions.Block;
import java.util.functions.FlatMapper;

import com.gs.collections.api.block.procedure.Procedure;
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
            .groupBy(customer ->
                customer.getOrders()
                    .stream()
                    .flatMap((Block<? super LineItem> sink, Order element) -> {element.getLineItems().forEach(sink);})
                    .map(LineItem::getValue)
                    .reduce(0.0, (x, y) -> Math.max(x,y)));
        Assert.assertEquals(2, multimap.keySet().size());
        Assert.assertEquals(
            Arrays.asList(
                this.company.getCustomerNamed("Fred"),
                this.company.getCustomerNamed("Bill")),
            multimap.get(50.0));
    }

    @Test
    public void flatMapVsForEach()
    {
        FastList<Interval> intervals = FastList.newListWith(Interval.oneTo(5), Interval.fromTo(6, 10));
        intervals.stream().forEach(System.out::println);
        intervals.stream().<Interval>flatMap((sink, each) -> {sink.apply(each.reverseThis());}).forEach(System.out::println);
        intervals.asLazy().flatCollect(Interval::reverseThis).forEach((Procedure<? super Integer>) System.out::println);
        intervals.stream().map(Interval::reverseThis).forEach(System.out::println);
    }

}
