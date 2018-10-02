package com.es.phoneshop.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOrder<T extends AbstractOrderItem> implements Serializable {
    protected volatile List<T> orderItems = new ArrayList<>();

    public void setOrderItems(List<T> orderItems) {
        this.orderItems = orderItems;
    }
    public synchronized void add(T orderItem) { orderItems.add(orderItem); }
    public synchronized void remove(T orderItem) { orderItems.remove(orderItem); }
}
