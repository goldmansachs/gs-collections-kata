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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class Exercise4Test extends CompanyDomainForKata
{
    /**
     * Improve {@link com.gs.collections.kata.Company#getOrders()} without breaking this test.
     */
    @Test
    public void improveGetOrders()
    {
        // Delete this line - it's a reminder
        Assert.assertEquals(5, this.company.getOrders().size());
    }

    /**
     * Get all items that have been ordered by anybody.
     */
    @Test
    public void findItemNames()
    {
        List<LineItem> allOrderedLineItems =
            this.company.getCustomers().flatMap(Customer::getOrders).flatMap(Order::getLineItems).into(new ArrayList<LineItem>());
        Set<String> actualItemNames = allOrderedLineItems.map(LineItem::getName).into(new HashSet<String>());

        Assert.assertTrue(actualItemNames instanceof Set);
        Assert.assertTrue(actualItemNames.getFirst() instanceof String);

        Set<String> expectedItemNames = new HashSet(Arrays.asList(
                "Shed", "big shed", "bowl", "cat", "cup", "chair", "dog",
                "goldfish", "gnome", "saucer", "shed", "sofa", "table"));
        Assert.assertEquals(expectedItemNames, actualItemNames);
    }

    @Test
    public void findCustomerNames()
    {
        List<String> names = this.company.getCustomers().map(Customer::getName).into(new ArrayList<String>());

        List<String> expectedNames = Arrays.asList("Fred", "Mary", "Bill");
        Assert.assertEquals(expectedNames, names);
    }
}
