package com.es.phoneshop.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    private ProductDao productDao = ArrayListProductDao.getInstance();
    private final Long productId = 1L, productIdWithNullPrice = 2L, productIdWithNullCurrency = 3L;
    private final Product product = new Product(productId, "A1B", "desc1", new BigDecimal("123.3"),
            Currency.getInstance(Locale.UK), 1);
    private final Product productWithNullPrice = new Product(productIdWithNullPrice, "A1B", "desc1",
            new BigDecimal(0), Currency.getInstance(Locale.UK), 1);
    private final Product productWithNullStock = new Product(productIdWithNullCurrency, "A1B", "desc1",
            new BigDecimal(12), Currency.getInstance(Locale.UK), 0);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void deleteProduct() {
        if (!productDao.findProducts().isEmpty()) {
            if (productDao.findProducts().size()>1) {
                productDao.remove(productIdWithNullPrice);
                productDao.remove(productIdWithNullCurrency);
            }
            productDao.remove(productId);
        }
    }

    @Test
    public void shouldFindProductsWithoutNullPrice() {
        productDao.save(product);
        productDao.save(productWithNullPrice);
        productDao.save(productWithNullStock);

        assertEquals(1, productDao.findProducts().size());
    }

    @Test
    public void shouldReturnProduct() {
        productDao.save(product);

        assertEquals(product, productDao.getProduct(productId));
    }

    @Test
    public void shouldThrowException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("There is no products with such id: " + productId);

        productDao.save(product);
        productDao.remove(productId);
        productDao.getProduct(productId);
    }

    @Test
    public void shouldNotReturnProduct(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("There is no products with such id: " + productId);

        productDao.getProduct(productId);
    }

    @Test
    public void shouldBeEquals() {
        assertEquals(ArrayListProductDao.getInstance(), ArrayListProductDao.getInstance());
    }
}