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
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class Exercise1Test extends CompanyDomainForKata
{
    @Test
    public void getCustomerNames()
    {
        Function<Customer, String> nameFunction = customer -> customer.getName();

        /**
         * Get the name of each of the company's customers.
         */
        List<Customer> customers = this.company.getCustomers();
        List<String> customerNames1 = customers.stream().map(nameFunction).into(new ArrayList<String>());
        List<String> customerNames2 = customers.stream().map(customer -> customer.getName()).into(new ArrayList<String>());
        List<String> customerNames3 = customers.stream().map(Customer::getName).into(new ArrayList<String>());

        List<String> expectedNames = Arrays.asList("Fred", "Mary", "Bill");
        Assert.assertEquals(expectedNames, customerNames1);
        Assert.assertEquals(expectedNames, customerNames2);
        Assert.assertEquals(expectedNames, customerNames3);
    }

    @Test
    public void getCustomerCities()
    {
        /**
         * Get the city for each of the company's customers. Use an anonymous inner class. Use the IDE to help you as
         * much as possible. Ctrl+space will help you implement an anonymous inner class. Implementing an interface is
         * ctrl+i in IntelliJ. Eclipse's ctrl+1 is auto-fix and works to implement interfaces.
         */
        List<String> customerCities = this.company.getCustomers().stream().map(Customer::getCity).into(new ArrayList<String>());

        List<String> expectedCities = Arrays.asList("London", "Liphook", "London");
        Assert.assertEquals(expectedCities, customerCities);
    }

    @Test
    public void getLondonCustomers()
    {
        /**
         * Which customers come from London? Get a collection of those which do. Use an anonymous inner class.
         */
        List<Customer> customersFromLondon = this.company.getCustomers().stream().filter(customer -> "London".equals(customer.getCity())).into(new ArrayList<Customer>());
        Assert.assertEquals("Should be 2 London customers", 2, customersFromLondon.size());
    }
}
