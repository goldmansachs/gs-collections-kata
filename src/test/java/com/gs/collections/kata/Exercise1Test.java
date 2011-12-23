package com.gs.collections.kata;

import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise1Test extends CompanyDomainForKata
{
    @Test
    public void getCustomerNames()
    {
        Function<Customer, String> nameFunction = new Function<Customer, String>()
        {
            @Override
            public String valueOf(Customer customer)
            {
                return customer.getName();
            }
        };

        /**
         * Get the name of each of the company's customers.
         */
        MutableList<Customer> customers = this.company.getCustomers();
        MutableList<String> customerNames = null;

        MutableList<String> expectedNames = FastList.newListWith("Fred", "Mary", "Bill");
        Assert.assertEquals(expectedNames, customerNames);
    }

    @Test
    public void getCustomerCities()
    {
        /**
         * Get the city for each of the company's customers. Use an anonymous inner class. Use the IDE to help you as
         * much as possible. Ctrl+space will help you implement an anonymous inner class. Implementing an interface is
         * ctrl+i in IntelliJ. Eclipse's ctrl+1 is auto-fix and works to implement interfaces.
         */
        MutableList<Customer> customers = this.company.getCustomers();
        MutableList<String> customerCities = null;

        MutableList<String> expectedCities = FastList.newListWith("London", "Liphook", "London");
        Assert.assertEquals(expectedCities, customerCities);
    }

    @Test
    public void getLondonCustomers()
    {
        /**
         * Which customers come from London? Get a collection of those which do. Use an anonymous inner class.
         */
        MutableList<Customer> customers = this.company.getCustomers();
        MutableList<Customer> customersFromLondon = null;
        Verify.assertSize("Should be 2 London customers", 2, customersFromLondon);
    }
}
