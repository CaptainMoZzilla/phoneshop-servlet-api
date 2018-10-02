package com.es.phoneshop.cart;

import com.es.phoneshop.model.Product;
import com.es.phoneshop.order.AbstractOrderItem;

import java.io.Serializable;
import java.util.Objects;

public class CartItem extends AbstractOrderItem implements Serializable {

    public CartItem(Product product, int quantity) {
        super(product, quantity);
    }
}
