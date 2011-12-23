package com.gs.collections.kata;

import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.block.factory.Predicates;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.test.Verify;
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
        MutableList<String> customerNames = this.company.getCustomers().collect(Customer.TO_NAME);

        MutableList<String> expectedNames = FastList.newListWith("Fred", "Mary", "Bill");
        Assert.assertEquals(expectedNames, customerNames);
    }

    @Test
    public void getCustomerCities()
    {
        /**
         * Get the city for each of the company's customers. This time move the {@link Function} to a
         * constant on {@link Customer}.
         */
        MutableList<String> customerCities = this.company.getCustomers().collect(Customer.TO_CITY);

        MutableList<String> expectedCities = FastList.newListWith("London", "Liphook", "London");
        Assert.assertEquals(expectedCities, customerCities);
    }

    @Test
    public void getLondonCustomers()
    {
        MutableList<Customer> customersFromLondon =
                this.company.getCustomers().select(Predicates.attributeEqual(Customer.TO_CITY, "London"));
        Verify.assertSize("Should be 2 London customers", 2, customersFromLondon);
    }
}
