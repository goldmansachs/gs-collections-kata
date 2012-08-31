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

import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.block.factory.Predicates;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.test.Verify;
import com.gs.collections.impl.utility.Iterate;
import org.junit.Assert;
import org.junit.Test;

public class Exercise6Test extends CompanyDomainForKata
{
    @Test
    public void filterOrderValues()
    {
        MutableList<Order> orders = this.company.getMostRecentCustomer().getOrders();
        /**
         * Same exercise but don't use static utility - refactor the type of orders instead.
         */
        MutableList<Double> orderValues = orders.collect(Order.TO_VALUE);
        MutableList<Double> filtered = orderValues.select(Predicates.greaterThan(1.5));
        Assert.assertEquals(FastList.newListWith(372.5, 1.75), filtered);
        Verify.assertInstanceOf(MutableList.class, this.company.getMostRecentCustomer().getOrders());
        this.company.getMostRecentCustomer().getOrders().add(null);
        Verify.assertContains("Don't return a copy from Customer.getOrders(). The field should be a MutableList.", null, this.company.getMostRecentCustomer().getOrders());
    }

    @Test
    public void filterOrders()
    {
        MutableList<Order> orders = this.company.getMostRecentCustomer().getOrders();
        /**
         * Same exercise but don't use static utility - refactor the type of orders instead.
         */
        MutableList<Order> filtered = orders.select(Predicates.attributeGreaterThan(Order.TO_VALUE, 2.0));
        Assert.assertEquals(FastList.newListWith(Iterate.getFirst(this.company.getMostRecentCustomer().getOrders())), filtered);
        Verify.assertInstanceOf(MutableList.class, this.company.getMostRecentCustomer().getOrders());
        this.company.getMostRecentCustomer().getOrders().add(null);
        Verify.assertContains("Don't return a copy from Customer.getOrders(). The field should be a MutableList.", null, this.company.getMostRecentCustomer().getOrders());
    }
}
