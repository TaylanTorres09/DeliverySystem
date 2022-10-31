package com.projeto.crud.springbootjpa.models;

import java.io.Serializable;

public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public String flavor;

    public Double price;

    private Product(){};

    private Product(String flavor, Double price) {
        this.flavor = flavor;
        this.price = price;
    };

    public String getFlavor() {
        return flavor;
    }

    public Double getPrice() {
        return price;
    }

}
