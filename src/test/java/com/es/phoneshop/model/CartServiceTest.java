package com.es.phoneshop.model;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartItem;
import com.es.phoneshop.cart.CartService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class CartServiceTest {

    private CartService cartService = CartService.getInstance();
    public HttpServletRequest request;
    public HttpSession session;

    private Cart cart = new Cart(), cartForRequest = new Cart();

    private long productId = 1L;
    private int quantity = 1;
    private Product product = new Product(productId, "A1B", "desc1", new BigDecimal("123.3"),
            Currency.getInstance(Locale.UK), 2);
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void clearCarts() {
        cart.getCartItems().clear();
        for (CartItem cartItem : cart.getCartItems()) {
            cart.remove(cartItem);
        }

        for (CartItem cartItem : cartForRequest.getCartItems()) {
            cartForRequest.remove(cartItem);
        }
    }

    @Test
    public void shouldAddCartItem() {
        cartService.add(cart, product, quantity);

        assertFalse(cart.getCartItems().isEmpty());
    }

    @Test
    public void shouldMakeNewQuantity() {
        cartService.add(cart, product, quantity );

        cartService.add(cart, product, quantity);

        assertNotSame(quantity, cart.getCartItems().get(0).getQuantity());
    }

    @Test
    public void shouldNotAddProductWithNegativeQuantity() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Product_has_negative_quantity");

        cartService.add(cart, product, -1);
    }

    @Test
    public void shouldNotAddProductWithQuantityBiggerThanStock() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Product_quantity_bigger_than_stock");

        cartService.add(cart, product, quantity * 100);
    }

    @Test
    public void shouldNotMakeNewQuantityBiggerThanStock() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Product_quantity_bigger_than_stock");
        cartService.add(cart, product, quantity);

        cartService.add(cart, product, quantity * 100);
    }

    @Test
    public void shouldMakeNewCart() {
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("cart")).thenReturn(null);

        Cart tempCart = cartService.getCart(request);
        Cart tempCart2 = cartService.getCart(request);

        assertNotSame(tempCart, tempCart2);
    }

    @Test
    public void shouldReturnTheSameCart() {
        request = Mockito.mock(HttpServletRequest.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("cart")).thenReturn(cart);

        Cart tempCart = cartService.getCart(request);
        Cart tempCart2 = cartService.getCart(request);

        assertEquals(tempCart, tempCart2);
    }
}