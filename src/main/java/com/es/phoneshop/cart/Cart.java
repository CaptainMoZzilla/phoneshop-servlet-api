package com.es.phoneshop.cart;

import com.es.phoneshop.order.AbstractOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AbstractOrder<CartItem> implements Serializable {
    public List<CartItem> getCartItems() {
        return new ArrayList<>(orderItems);
    }
}
