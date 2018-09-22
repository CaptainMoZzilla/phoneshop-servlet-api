package com.es.phoneshop.order;

import com.es.phoneshop.model.Product;

import java.io.Serializable;

public class OrderItem extends AbstractOrderItem implements Serializable {
    public OrderItem(Product product, int quantity) {
        super(product, quantity);
    }

}
