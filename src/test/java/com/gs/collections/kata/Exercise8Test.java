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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Accumulators;

import com.gs.collections.impl.bag.mutable.HashBag;
import org.junit.Assert;
import org.junit.Test;

public class Exercise8Test extends CompanyDomainForKata
{
    /**
     * Create a multimap where the keys are the names of cities and the values are the customers from those cities.
     */
    @Test
    public void customersByCity()
    {
        // Implemented groupBy using a Map on the super class for all tests.
        Map<String, Collection<Customer>> multimap =
            this.company.getCustomers()
                .stream()
                .accumulate(Accumulators.<Customer, String>groupBy(Customer::getCity));

        Assert.assertEquals(HashBag.newBagWith(this.company.getCustomerNamed("Mary")), HashBag.newBag(multimap.get("Liphook")));
        Assert.assertEquals(
                HashBag.newBagWith(this.company.getCustomerNamed("Fred"),this.company.getCustomerNamed("Bill")),
                HashBag.newBag(multimap.get("London")));
    }

    @Test
    public void mapOfItemsToSuppliers()
    {
        final Map<String, List<Supplier>> itemsToSuppliers = new HashMap<>();

        Arrays.asList(this.company.getSuppliers()).forEach(supplier -> {
            Arrays.asList(supplier.getItemNames()).forEach(itemName ->
                {
                    List<Supplier> suppliersForItem;
                    if (itemsToSuppliers.containsKey(itemName))
                    {
                        suppliersForItem = itemsToSuppliers.get(itemName);
                    }
                    else
                    {
                        suppliersForItem = new ArrayList<>();
                        itemsToSuppliers.put(itemName, suppliersForItem);
                    }

                    suppliersForItem.add(supplier);
                }
            );}
        );
        Assert.assertEquals("should be 2 suppliers for sofa", 2, itemsToSuppliers.get("sofa").size());
    }
}
