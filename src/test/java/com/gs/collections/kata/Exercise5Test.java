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

import java.util.List;

import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.list.primitive.DoubleList;
import com.gs.collections.impl.block.factory.primitive.DoublePredicates;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.list.mutable.primitive.DoubleArrayList;
import com.gs.collections.impl.utility.ArrayIterate;
import com.gs.collections.impl.utility.Iterate;
import com.gs.collections.impl.utility.ListIterate;
import org.junit.Assert;
import org.junit.Test;

public class Exercise5Test extends CompanyDomainForKata
{
    /**
     * Solve this without changing the return type of {@link Company#getSuppliers()}. Find the appropriate method on
     * {@link ArrayIterate}.
     */
    @Test
    public void findSupplierNames()
    {
        MutableList<String> supplierNames = ArrayAdapter.adapt(this.company.getSuppliers()).collect(Supplier::getName);

        MutableList<String> expectedSupplierNames = FastList.newListWith(
            "Shedtastic",
            "Splendid Crocks",
            "Annoying Pets",
            "Gnomes 'R' Us",
            "Furniture Hamlet",
            "SFD",
            "Doxins");
        Assert.assertEquals(expectedSupplierNames, supplierNames);
    }

    /**
     * Create a {@link Predicate} for Suppliers that supply more than 2 items. Find the number of suppliers that
     * satisfy that Predicate.
     */
    @Test
    public void countSuppliersWithMoreThanTwoItems()
    {
        Predicate<Supplier> moreThanTwoItems = supplier -> supplier.getItemNames().length > 2;
        int suppliersWithMoreThanTwoItems = ArrayIterate.count(this.company.getSuppliers(), moreThanTwoItems);
        Assert.assertEquals("suppliers with more than 2 items", 5, suppliersWithMoreThanTwoItems);
    }

    /**
     * Try to solve this without changing the return type of {@link Supplier#getItemNames()}.
     */
    @Test
    public void whoSuppliesSandwichToaster()
    {
        // Create a Predicate that will check to see if a Supplier supplies a "sandwich toaster".
        Predicate<Supplier> suppliesToaster = supplier -> supplier.supplies("sandwich toaster");

        // Find one supplier that supplies toasters.
        Supplier toasterSupplier = ArrayIterate.detect(this.company.getSuppliers(), suppliesToaster);
        Assert.assertNotNull("toaster supplier", toasterSupplier);
        Assert.assertEquals("Doxins", toasterSupplier.getName());
    }

    @Test
    public void filterOrderValues()
    {
        /**
         * Get the order values that are greater than 1.5.
         */
        DoubleList orderValues = this.company
                .getMostRecentCustomer()
                .getOrders()
                .asLazy()
                .collectDouble(Order::getValue)
                .select(DoublePredicates.greaterThan(1.5))
                .toSortedList();
        Assert.assertEquals(DoubleArrayList.newListWith(1.75d, 372.5), orderValues);
    }

    @Test
    public void filterOrders()
    {
        List<Order> orders = this.company.getMostRecentCustomer().getOrders();
        /**
         * Get the actual orders (not their double values) where those orders have a value greater than 2.0.
         */
        MutableList<Order> filtered = ListIterate.select(orders, order -> order.getValue() > 2.0);
        Assert.assertEquals(FastList.newListWith(Iterate.getFirst(this.company.getMostRecentCustomer().getOrders())), filtered);
    }
}
