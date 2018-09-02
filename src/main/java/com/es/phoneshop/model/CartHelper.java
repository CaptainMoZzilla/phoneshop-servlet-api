package com.es.phoneshop.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class CartHelper {
    private static ProductDao productDao = ArrayListProductDao.getInstance();
    private static CartService cartService = CartService.getInstance();

    public static void addSomeCartItems(Cart cart){
        for(Product product : productDao.findProducts())
            cartService.addToCart(cart, product, (int) (Math.random()*10));
    }
}
