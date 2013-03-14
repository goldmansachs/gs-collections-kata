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

import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.list.primitive.DoubleList;
import com.gs.collections.impl.block.factory.primitive.DoublePredicates;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.list.mutable.primitive.DoubleArrayList;
import com.gs.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

public class Exercise6Test extends CompanyDomainForKata
{
    @Test
    public void filterOrderValues()
    {
        /**
         * Same exercise but don't use static utility - refactor the type of orders and {@link Customer#getOrders()}
         * instead.
         * Get the order values that are greater than 1.5.
         */
        DoubleList filtered = this.company.getMostRecentCustomer()
                .getOrders()
                .asLazy()
                .collectDouble(Order::getValue)
                .select(DoublePredicates.greaterThan(1.5))
                .toSortedList();
        Assert.assertEquals(DoubleArrayList.newListWith(1.75, 372.5), filtered);
    }

    @Test
    public void filterOrders()
    {
        MutableList<Order> orders = this.company.getMostRecentCustomer().getOrders();
        /**
         * Same exercise but don't use static utility - refactor the type of orders and {@link Customer#getOrders()}
         * instead.
         * Get the actual orders (not their double values) where those orders have a value greater than 2.0.
         */
        MutableList<Order> filtered = orders.select(order -> order.getValue() > 2.0);
        Assert.assertEquals(FastList.newListWith(this.company.getMostRecentCustomer().getOrders().getFirst()), filtered);
    }
}
