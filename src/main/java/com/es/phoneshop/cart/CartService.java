package com.es.phoneshop.cart;

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

    public void add(Cart cart, Product product, int quantity) {
        synchronized (cart) {
            CartItem cartItem = new CartItem(product, quantity);
            int indexOfCartItem = cart.getCartItems().indexOf(cartItem);

            if (quantity <= 0) {
                throw new IllegalArgumentException("Product_has_negative_quantity");
            }

            if (indexOfCartItem == -1) {
                if (quantity > product.getStock()) {
                    throw new IllegalArgumentException("Product_quantity_bigger_than_stock");
                }
                cart.add(cartItem);
            } else {
                if (quantity + cart.getCartItems().get(indexOfCartItem).getQuantity() > product.getStock()) {
                    throw new IllegalArgumentException("Product_quantity_bigger_than_stock");
                } else {
                    cartItem.setQuantity(quantity + cart.getCartItems().get(indexOfCartItem).getQuantity());
                    cart.set(indexOfCartItem, cartItem);
                }
            }
        }
    }

    public void update(Cart cart, Product product, Integer quantity) {
        synchronized (cart) {
            if (quantity > product.getStock()) {
                throw new IllegalArgumentException("Product_quantity_bigger_than_stock");
            } else {
                cart.getCartItems().stream()
                        .filter(cartItem -> cartItem.getProduct().equals(product))
                        .findFirst().get().setQuantity(quantity);
            }
        }

    }
    public void delete(Cart cart, Long productId) {
        synchronized (cart) {
            cart.remove(cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getProduct().getId() == productId)
                    .findFirst().get());
        }
    }
}
