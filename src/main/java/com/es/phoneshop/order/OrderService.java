package com.es.phoneshop.order;

import com.es.phoneshop.cart.Cart;

public interface OrderService {
    Order placeOrder(Cart cart);
    Order getOrder(String id);
}
