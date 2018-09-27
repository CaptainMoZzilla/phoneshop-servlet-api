package com.es.phoneshop.comparePage;

import com.es.phoneshop.model.Product;

import java.util.ArrayDeque;

public class CompareQue {
    private static final CompareQue INSTANCE = new CompareQue();
    private ArrayDeque<Product> productQue = new ArrayDeque<>();

    private CompareQue() {}

    public static CompareQue getInstance() { return INSTANCE; }

    public ArrayDeque<Product> getProductQue() {
        return new ArrayDeque<>(productQue);
    }

    public synchronized boolean addToQue(Product product) {
        if (productQue.contains(product))
            return false;

        if (productQue.size() == 3) {
            productQue.pollFirst();
        }

        productQue.add(product);
        return true;
    }
}
