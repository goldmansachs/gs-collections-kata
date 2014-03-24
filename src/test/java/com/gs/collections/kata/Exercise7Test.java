/*
 * Copyright 2014 Goldman Sachs.
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

import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.api.list.primitive.DoubleList;
import com.gs.collections.impl.block.factory.Predicates;
import com.gs.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise7Test extends CompanyDomainForKata {
    /**
     * Get a list of the customers' total order values, sorted. Check out the implementation of {@link
     * Customer#getTotalOrderValue()} and {@link Order#getValue()} .
     */
    @Test
    public void sortedTotalOrderValue() {
        DoubleList sortedTotalValues =
                this.company.getCustomers().collectDouble(Customer::getTotalOrderValue).sortThis();

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assert.assertEquals("Highest total order value", 857.0, sortedTotalValues.getLast(), 0.0);
        Assert.assertEquals("Lowest total order value", 71.0, sortedTotalValues.getFirst(), 0.0);
    }

    /**
     * Find the max total order value across all customers.
     */
    @Test
    public void maximumTotalOrderValue() {
        double maximumTotalOrderValue = this.company.getCustomers().asLazy().collectDouble(Customer::getTotalOrderValue).max();
        Assert.assertEquals("max value", 857.0d, maximumTotalOrderValue, 0.0d);
    }

    /**
     * Find the customer with the highest total order value.
     */
    @Test
    public void customerWithMaxTotalOrderValue() {
        Customer customerWithMaxTotalOrderValue = this.company.getCustomers().maxBy(Customer::getTotalOrderValue);
        Assert.assertEquals(this.company.getCustomerNamed("Mary"), customerWithMaxTotalOrderValue);
    }

    /**
     * Create some code to get the company's supplier names as a tilde delimited string.
     */
    @Test
    public void supplierNamesAsTildeDelimitedString() {
        String tildeSeparatedNames = this.company.getSuppliers().collect(Supplier::getName).makeString("~");
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
    public void deliverOrdersToLondon() {
        Procedure<Order> deliver = Order::deliver;
        this.company.getCustomers()
                .asLazy()
                .selectWith(Customer::livesIn, "London")
                .flatCollect(Customer::getOrders)
                .forEach(deliver);
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Fred").getOrders(), Order::isDelivered);
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Mary").getOrders(), Predicates.<Order>not(Order::isDelivered));
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Bill").getOrders(), Order::isDelivered);
    }
}
