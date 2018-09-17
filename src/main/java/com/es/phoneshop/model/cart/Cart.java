package com.es.phoneshop.model.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private volatile List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public synchronized void addToCart(CartItem cartItem) { cartItems.add(cartItem); }
    public synchronized void setCartItem(int index, CartItem cartItem) { cartItems.set(index, cartItem); }
    public synchronized void removeCartItem(CartItem cartItem) { cartItems.remove(cartItem); }
}
