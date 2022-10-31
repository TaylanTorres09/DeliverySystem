package com.projeto.crud.springbootjpa.models;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Component
public class Product {
    
    @NotEmpty
    private String flavor;

    @NotNull
    private Double price;

}
