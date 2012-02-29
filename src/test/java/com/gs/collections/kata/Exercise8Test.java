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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.gs.collections.api.multimap.Multimap;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
import com.gs.collections.impl.test.Verify;
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
        Map<String, List<Customer>> multimap = this.groupBy(this.company.getCustomers(), Customer::getCity);

        Assert.assertEquals(Arrays.asList(this.company.getCustomerNamed("Mary")), multimap.get("Liphook"));
        Assert.assertEquals(
            Arrays.asList(
                this.company.getCustomerNamed("Fred"),
                this.company.getCustomerNamed("Bill")),
            multimap.get("London"));
    }

    @Test
    public void mapOfItemsToSuppliers()
    {
        /**
         * Change itemsToSuppliers to a MutableMultimap<String, Supplier> - Too verbose to write using JDK classes
         */
        final Multimap<String, Supplier> itemsToSuppliers =
            ArrayAdapter.adapt(this.company.getSuppliers()).groupByEach(supplier -> ArrayAdapter.adapt(supplier.getItemNames()));
        Verify.assertIterableSize("should be 2 suppliers for sofa", 2, itemsToSuppliers.get("sofa"));
    }
}
