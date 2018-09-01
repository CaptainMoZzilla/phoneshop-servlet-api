package com.es.phoneshop.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CartService {
    private static volatile CartService instance = new CartService();
    private static final String CART_ATTRIBUTE_NAME = "cart";

    private CartService() { }

    public static CartService getInstance() {
        return instance;
    }

    public Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE_NAME);

        if(cart == null) {
            synchronized (Cart.class) {
                if(cart == null) {
                    cart = new Cart();
                    session.setAttribute(CART_ATTRIBUTE_NAME, cart);
                }
            }
        }
        return cart;
    }

    public void addToCart(Cart cart, Product product, int quantity) {
        CartItem cartItem = new CartItem(product, quantity);
        int indexOfCartItem = cart.getCartItems().indexOf(cartItem);

        if (indexOfCartItem == -1) {
            cart.getCartItems().add(cartItem);
        } else {
            cartItem.setQuantity(quantity + cart.getCartItems().get(indexOfCartItem).getQuantity());
            cart.getCartItems().set(indexOfCartItem, cartItem);
        }
    }

}
