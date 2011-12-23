package com.gs.collections.kata;

import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.block.factory.Predicates;
import com.gs.collections.impl.test.Verify;
import com.gs.collections.impl.utility.ArrayIterate;
import org.junit.Assert;
import org.junit.Test;

public class Exercise7Test extends CompanyDomainForKata
{
    /**
     * Get a list of the customers' total order values, sorted. Check out the implementation of {@link
     * Customer#getTotalOrderValue()} and {@link Order#getValue()} .
     */
    @Test
    public void sortedTotalOrderValue()
    {
        MutableList<Double> sortedTotalValues =
                this.company.getCustomers().collect(Customer.TO_TOTAL_ORDER_VALUE).toSortedList();

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assert.assertEquals("Highest total order value", Double.valueOf(857.0), sortedTotalValues.getLast());
        Assert.assertEquals("Lowest total order value", Double.valueOf(71.0), sortedTotalValues.getFirst());
    }

    /**
     * Find the max total order value across all customers.
     */
    @Test
    public void maximumTotalOrderValue()
    {
        Double maximumTotalOrderValue =
                this.company.getCustomers().collect(Customer.TO_TOTAL_ORDER_VALUE).max();
        Assert.assertEquals("max value", Double.valueOf(857.0), maximumTotalOrderValue);
    }

    /**
     * Find the customer with the highest total order value.
     */
    @Test
    public void customerWithMaxTotalOrderValue()
    {
        Customer customerWithMaxTotalOrderValue =
                this.company.getCustomers().maxBy(Customer.TO_TOTAL_ORDER_VALUE);
        Assert.assertEquals(this.company.getCustomerNamed("Mary"), customerWithMaxTotalOrderValue);
    }

    /**
     * Create some code to get the company's supplier names as a tilde delimited string.
     */
    @Test
    public void supplierNamesAsTildeDelimitedString()
    {
        String tildeSeparatedNames =
                ArrayIterate.collect(this.company.getSuppliers(), Supplier.TO_NAME).makeString("~");
        Assert.assertEquals(
                "tilde separated names",
                "Shedtastic~Splendid Crocks~Annoying Pets~Gnomes 'R' Us~Furniture Hamlet~SFD~Doxins",
                tildeSeparatedNames);
    }

    /**
     * Deliver all orders going to customers from London.
     *
     * @see Order#deliver()
     */
    @Test
    public void deliverOrdersToLondon()
    {
        this.company.getCustomers()
                .select(Predicates.attributeEqual(Customer.TO_CITY, "London"))
                .flatCollect(Customer.TO_ORDERS)
                .forEach(Order.DELIVER);

        Verify.assertAllSatisfy(this.company.getCustomerNamed("Fred").getOrders(), Order.IS_DELIVERED);
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Mary").getOrders(), Predicates.not(Order.IS_DELIVERED));
        Verify.assertAllSatisfy(this.company.getCustomerNamed("Bill").getOrders(), Order.IS_DELIVERED);
    }
}
