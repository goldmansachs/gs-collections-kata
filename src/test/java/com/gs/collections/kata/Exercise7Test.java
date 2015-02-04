/*
 * Copyright 2015 Goldman Sachs.
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

import com.gs.collections.api.map.MutableMap;
import com.gs.collections.api.multimap.list.MutableListMultimap;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise7Test extends CompanyDomainForKata
{
    /**
     * Create a multimap where the keys are the names of cities and the values are the customers from those cities.
     */
    @Test
    public void customersByCity()
    {
        // Notice that the second generic type is Customer, not List<Customer>
        MutableListMultimap<String, Customer> multimap = null;

        Assert.assertEquals(FastList.newListWith(this.company.getCustomerNamed("Mary")), multimap.get("Liphook"));
        Assert.assertEquals(
                FastList.newListWith(
                        this.company.getCustomerNamed("Fred"),
                        this.company.getCustomerNamed("Bill")),
                multimap.get("London"));
    }

    @Test
    public void mapOfItemsToSuppliers()
    {
        Assert.fail("Refactor this as part of Exercise 7");
        /**
         * Change itemsToSuppliers to a MutableMultimap<String, Supplier>
         */
        final MutableMap<String, List<Supplier>> itemsToSuppliers = UnifiedMap.newMap();

        for (Supplier supplier : this.company.getSuppliers())
        {
            for (String itemName : supplier.getItemNames())
            {
                List<Supplier> suppliersForItem;
                if (itemsToSuppliers.containsKey(itemName))
                {
                    suppliersForItem = itemsToSuppliers.get(itemName);
                }
                else
                {
                    suppliersForItem = FastList.newList();
                    itemsToSuppliers.put(itemName, suppliersForItem);
                }

                suppliersForItem.add(supplier);
            }
        }
        Verify.assertIterableSize("should be 2 suppliers for sofa", 2, itemsToSuppliers.get("sofa"));
    }

    @Test
    public void reminder()
    {
        Assert.fail("Refactor setUpCustomersAndOrders() in the super class to not have so much repetition.");
        // Delete this whole method when you're done. It's just a reminder.
    }
}
