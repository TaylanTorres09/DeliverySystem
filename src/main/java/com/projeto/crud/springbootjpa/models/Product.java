package com.projeto.crud.springbootjpa.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public abstract class Product {
    
    @NotEmpty
    private String flavor;

    @NotNull
    private Double price;

}
