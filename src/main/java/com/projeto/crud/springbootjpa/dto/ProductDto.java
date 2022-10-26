package com.projeto.crud.springbootjpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    
    @NotBlank
    private String name;

    @NotNull
    private Double price;
    
    private String imgUrl;    

    @NotBlank
    private String category;
}