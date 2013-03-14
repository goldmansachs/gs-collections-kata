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

import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.function.primitive.DoubleFunction;
import com.gs.collections.api.list.MutableList;
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

    public static final DoubleFunction<Customer> TO_TOTAL_ORDER_VALUE =
            new DoubleFunction<Customer>()
            {
                public double doubleValueOf(Customer customer)
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
        return this.orders.sumOfDouble(Order.TO_VALUE);
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
