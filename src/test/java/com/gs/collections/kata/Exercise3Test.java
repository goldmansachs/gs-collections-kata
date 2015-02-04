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
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

import static java.util.stream.Collectors.toList;

public class Exercise3Test extends CompanyDomainForKata
{
    /**
     * Set up a {@link Predicate} that tests to see if a {@link Customer}'s city is "London"
     */
    private static final Predicate<Customer> CUSTOMER_FROM_LONDON = customer -> customer.getCity().equals("London");

    @Test
    public void customerFromLondonPredicate()
    {
        String predicateClass = CUSTOMER_FROM_LONDON.getClass().getSimpleName();
        Assert.assertTrue(
                "Solution should use a lambda but used " + predicateClass,
                predicateClass.startsWith("Exercise3Test$$Lambda"));

        Customer customerFromLondon = new Customer("test customer", "London");

        Assert.assertTrue(
                "CUSTOMER_FROM_LONDON should accept Customers where city is London",
                CUSTOMER_FROM_LONDON.test(customerFromLondon));
    }

    /**
     * Do any customers come from London?
     */
    @Test
    public void doAnyCustomersLiveInLondon()
    {
        boolean anyCustomersFromLondon =
                this.company.getCustomers().stream().anyMatch(customer -> "London".equals(customer.getCity()));
        Assert.assertTrue(anyCustomersFromLondon);
    }

    /**
     * Do all customers come from London?
     */
    @Test
    public void doAllCustomersLiveInLondon()
    {
        boolean allCustomersFromLondon =
                this.company.getCustomers().stream().allMatch(customer -> "London".equals(customer.getCity()));
        Assert.assertFalse(allCustomersFromLondon);
    }

    /**
     * How many customers come from London?
     */
    @Test
    public void howManyCustomersLiveInLondon()
    {
        long numberOfCustomerFromLondon = this.company.getCustomers().stream()
                .filter(customer -> "London".equals(customer.getCity()))
                .count();
        Assert.assertEquals("Should be 2 London customers", 2, numberOfCustomerFromLondon);
    }

    /**
     * Which customers come from London? Get a collection of those which do.
     */
    @Test
    public void getLondonCustomers()
    {
        List<Customer> customersFromLondon = this.company.getCustomers()
                .stream()
                .filter(customer -> "London".equals(customer.getCity()))
                .collect(toList());
        Assert.assertEquals("Should be 2 London customers", 2, customersFromLondon.size());
    }

    /**
     * Which customers do not come from London? Get a collection of those which don't.
     */
    @Test
    public void getCustomersWhoDontLiveInLondon()
    {
        List<Customer> customersNotFromLondon = this.company.getCustomers()
                .stream()
                .filter(customer -> !"London".equals(customer.getCity()))
                .collect(toList());
        Assert.assertEquals("customers not from London", 1, customersNotFromLondon.size());
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
        Assert.assertNull("Should be null as there is no customer called Pete", pete);
    }
}
