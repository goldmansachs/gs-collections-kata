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

import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise3Test extends CompanyDomainForKata
{
    /**
     * Do any customers come from London?
     */
    @Test
    public void doAnyCustomersLiveInLondon()
    {
        boolean anyCustomersFromLondon = this.company.getCustomers().anySatisfyWith(Customer::livesIn, "London");
        Assert.assertTrue(anyCustomersFromLondon);
    }

    /**
     * Do all customers come from London?
     */
    @Test
    public void doAllCustomersLiveInLondon()
    {
        boolean allCustomersFromLondon = this.company.getCustomers().allSatisfyWith(Customer::livesIn, "London");
        Assert.assertFalse(allCustomersFromLondon);
    }

    /**
     * How many customers come from London?
     */
    @Test
    public void howManyCustomersLiveInLondon()
    {
        int numberOfCustomerFromLondon = this.company.getCustomers().countWith(Customer::livesIn, "London");
        Assert.assertEquals("Should be 2 London customers", 2, numberOfCustomerFromLondon);
    }

    /**
     * Which customers come from London? Get a collection of those which do.
     */
    @Test
    public void getLondonCustomers()
    {
        MutableList<Customer> customersFromLondon = this.company.getCustomers().selectWith(Customer::livesIn, "London");
        Verify.assertSize("Should be 2 London customers", 2, customersFromLondon);
    }

    /**
     * Which customers do not come from London? Get a collection of those which don't.
     */
    @Test
    public void getCustomersWhoDontLiveInLondon()
    {
        MutableList<Customer> customersNotFromLondon = this.company.getCustomers().rejectWith(Customer::livesIn, "London");
        Verify.assertSize("customers not from London", 1, customersNotFromLondon);
    }

    /**
     * Implement {@link Company#getCustomerNamed(String)} and get this test to pass.
     */
    @Test
    public void findMary()
    {
        Customer mary = this.company.getCustomerNamed("Mary");
        Assert.assertEquals("customer's name should be Mary", "Mary", mary.getName());
    }

    /**
     * Implement {@link Company#getCustomerNamed(String)} and get this test to pass.
     */
    @Test
    public void findPete()
    {
        Customer pete = this.company.getCustomerNamed("Pete");
        Assert.assertNull(
            "Should be null as there is no customer called Pete",
            pete);
    }
}
