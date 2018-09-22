package com.es.phoneshop.order;

import com.es.phoneshop.model.Product;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractOrderItem implements Serializable, Cloneable {
    private Product product;
    private int quantity;

    public AbstractOrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractOrderItem abstractOrderItem = (AbstractOrderItem) o;
        return Objects.equals(product, abstractOrderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
