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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class Exercise2Test extends CompanyDomainForKata
{
    @Test
    public void getCustomerNames()
    {
        /**
         * Get the name of each of the company's customers. This time move the {@link Function} to a
         * constant on {@link Customer}.
         */
        List<String> customerNames = this.company.getCustomers().stream().map(Customer::getName).into(new ArrayList<String>());

        List<String> expectedNames = Arrays.asList("Fred", "Mary", "Bill");
        Assert.assertEquals(expectedNames, customerNames);
    }

    @Test
    public void getCustomerCities()
    {
        /**
         * Get the city for each of the company's customers. This time move the {@link Function} to a
         * constant on {@link Customer}.
         */
        List<String> customerCities = this.company.getCustomers().stream().map(Customer::getCity).into(new ArrayList<String>());

        List<String> expectedCities = Arrays.asList("London", "Liphook", "London");
        Assert.assertEquals(expectedCities, customerCities);
    }

    @Test
    public void getLondonCustomers()
    {
        List<Customer> customersFromLondon = this.company.getCustomers().stream().filter(customer -> "London".equals(customer.getCity())).into(new ArrayList<Customer>());
        Assert.assertEquals("Should be 2 London customers", 2, customersFromLondon.size());
    }
}
