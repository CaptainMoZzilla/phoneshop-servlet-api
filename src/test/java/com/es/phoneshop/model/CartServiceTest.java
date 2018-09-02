package com.es.phoneshop.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class CartServiceTest {

    private CartService cartService = CartService.getInstance();
    private Cart cart = new Cart();
    private long productId = 1L;
    private int quantity = 20;
    private Product product = new Product(productId, "A1B", "desc1", new BigDecimal("123.3"),
            Currency.getInstance(Locale.UK), 1);

    @Before
    public void clearCartItems() {
        cart.getCartItems().clear();
    }

    @Test
    public void shouldAddCartItem() {
        cartService.addToCart(cart, product, quantity);

        assertFalse(cart.getCartItems().isEmpty());
    }

}