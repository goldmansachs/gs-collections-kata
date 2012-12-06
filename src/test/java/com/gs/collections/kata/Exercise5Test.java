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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

public class Exercise5Test extends CompanyDomainForKata
{
    /**
     * Solve this without changing the return type of {@link Company#getSuppliers()}.
     */
    @Test
    public void findSupplierNames()
    {
        List<String> supplierNames = Arrays.asList(this.company.getSuppliers()).stream().map(Supplier::getName).into(new ArrayList<String>());

        List<String> expectedSupplierNames = Arrays.asList(
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
        int suppliersWithMoreThanTwoItems = Arrays.asList(this.company.getSuppliers()).stream().filter(moreThanTwoItems).into(new ArrayList<Supplier>()).size();
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
        Supplier toasterSupplier = Arrays.asList(this.company.getSuppliers()).stream().filter(suppliesToaster).findFirst().get();
        Assert.assertNotNull("toaster supplier", toasterSupplier);
        Assert.assertEquals("Doxins", toasterSupplier.getName());
    }

    @Test
    public void filterOrderValues()
    {
        List<Order> orders = this.company.getMostRecentCustomer().getOrders();
        /**
         * Get the order values that are greater than 1.5.
         */
        List<Double> filtered = orders.stream().map(Order::getValue).filter(orderValue -> orderValue > 1.5).into(new ArrayList<Double>());
        Assert.assertEquals(Arrays.asList(372.5, 1.75), filtered);
    }

    @Test
    public void filterOrders()
    {
        List<Order> orders = this.company.getMostRecentCustomer().getOrders();
        /**
         * Get the actual orders (not their double values) where those orders have a value greater than 2.0.
         */
        List<Order> filtered = orders.stream().filter(order -> order.getValue() > 2.0).into(new ArrayList<Order>());
        Assert.assertEquals(Arrays.asList(this.company.getMostRecentCustomer().getOrders().stream().findFirst().get()), filtered);
    }
}
