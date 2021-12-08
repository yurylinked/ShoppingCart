package com.entity;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable {// ShoppingCartItem это корзина пользователя? а что тогда ShoppingCart?
    private static final long serialVersionUID = 6436798264138502851L;
    private int idProduct;
    private int count;
    public ShoppingCartItem() { //зачем такой конструктор? зачем обращение к родительскому?
        super();
    }
    public ShoppingCartItem(int idProduct, int count) {
        super();
        this.idProduct = idProduct;
        this.count = count;
    }
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        return "ShoppingCartItem [idProduct=" + idProduct + ", count=" + count + "]";
    }
}