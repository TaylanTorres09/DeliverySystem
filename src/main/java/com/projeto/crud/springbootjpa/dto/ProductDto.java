package com.projeto.crud.springbootjpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDto {
    
    @NotBlank
    private String nameProduct;

    @NotBlank
    private String descriptionProduct;

    @NotBlank
    private Double priceProduct;
    
    private String imgUrlProduct;

}
