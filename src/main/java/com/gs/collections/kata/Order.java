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

import java.util.List;

import com.gs.collections.api.bag.Bag;
import com.gs.collections.api.bag.MutableBag;
import com.gs.collections.impl.bag.mutable.HashBag;

/**
 * Has a number, a {@link Customer}, a {@link List} of {@link LineItem}s, and a boolean that states whether or not the order
 * has been delivered.  There is a class variable that contains the next order number.
 */
public class Order
{
    private static int nextOrderNumber = 1;

    private final int orderNumber;
    private boolean isDelivered;
    private final MutableBag<LineItem> lineItems = HashBag.newBag();

    public Order()
    {
        this.orderNumber = nextOrderNumber;
        nextOrderNumber += 1;
    }

    public static void resetNextOrderNumber()
    {
        nextOrderNumber = 1;
    }

    public void deliver()
    {
        this.isDelivered = true;
    }

    public boolean isDelivered()
    {
        return this.isDelivered;
    }

    public void addLineItem(LineItem aLineItem)
    {
        this.lineItems.add(aLineItem);
    }

    public Bag<LineItem> getLineItems()
    {
        return this.lineItems;
    }

    @Override
    public String toString()
    {
        return "order " + this.orderNumber + " items: " + this.lineItems.size();
    }

    public double getValue()
    {
        return this.lineItems.sumOfDouble(LineItem::getValue);
    }

    public void addLineItems(LineItem item, int count)
    {
        this.lineItems.addOccurrences(item, count);
    }
}
