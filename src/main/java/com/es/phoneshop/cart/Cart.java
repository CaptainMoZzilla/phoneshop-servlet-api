package com.es.phoneshop.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private volatile List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public synchronized void add(CartItem cartItem) { cartItems.add(cartItem); }
    public synchronized void set(int index, CartItem cartItem) { cartItems.set(index, cartItem); }
    public synchronized void remove(CartItem cartItem) { cartItems.remove(cartItem); }
}
