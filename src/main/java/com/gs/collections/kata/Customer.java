package com.gs.collections.kata;

import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.block.function.AddFunction;
import com.gs.collections.impl.list.mutable.FastList;

/**
 * Customers have a name, city and a list of {@link Order}s.
 */
public class Customer
{
    public static final Function<Customer, String> TO_NAME = new Function<Customer, String>()
    {
        @Override
        public String valueOf(Customer customer)
        {
            return customer.name;
        }
    };

    public static final Function<Customer, String> TO_CITY = new Function<Customer, String>()
    {
        @Override
        public String valueOf(Customer customer)
        {
            return customer.city;
        }
    };

    public static final Function<Customer, Double> TO_TOTAL_ORDER_VALUE =
            new Function<Customer, Double>()
            {
                @Override
                public Double valueOf(Customer customer)
                {
                    return customer.getTotalOrderValue();
                }
            };

    public static final Function<Customer, MutableList<Order>> TO_ORDERS = new Function<Customer, MutableList<Order>>()
    {
        @Override
        public MutableList<Order> valueOf(Customer customer)
        {
            return customer.getOrders();
        }
    };

    private final String name;
    private final String city;
    private final MutableList<Order> orders = FastList.newList();

    public Customer(String name, String city)
    {
        this.name = name;
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getName()
    {
        return this.name;
    }

    public MutableList<Order> getOrders()
    {
        return this.orders;
    }

    public void addOrder(Order anOrder)
    {
        this.orders.add(anOrder);
    }

    public double getTotalOrderValue()
    {
        MutableList<Double> orderValues = this.orders.collect(new Function<Order, Double>()
        {
            @Override
            public Double valueOf(Order order)
            {
                return order.getValue();
            }
        });
        return orderValues.injectInto(0.0, AddFunction.DOUBLE_TO_DOUBLE);
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
