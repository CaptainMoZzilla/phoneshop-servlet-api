package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CartService {
    private static final CartService INSTANCE = new CartService();
    private static final String CART_ATTRIBUTE_NAME = "cart";

    private CartService() {
    }

    public static CartService getInstance() {
        return INSTANCE;
    }

    public Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE_NAME);

        if (cart == null) {
            synchronized (request.getSession()) {
                cart = (Cart) session.getAttribute(CART_ATTRIBUTE_NAME);
                if (cart == null) {
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

        if (quantity <= 0) {
            throw new IllegalArgumentException("Negative");
        }

        if (indexOfCartItem == -1) {
            if (quantity > product.getStock()) {
                throw new IllegalArgumentException("VeryBig");
            }
            cart.addToCart(cartItem);
        } else {

            if (quantity + cart.getCartItems().get(indexOfCartItem).getQuantity() > product.getStock()) {
                throw new IllegalArgumentException("VeryBig");
            }
            cartItem.setQuantity(quantity + cart.getCartItems().get(indexOfCartItem).getQuantity());
            cart.setCartItem(indexOfCartItem, cartItem);
        }
    }
}
