package com.projeto.crud.springbootjpa.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.projeto.crud.springbootjpa.models.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderDto {

    private Instant moment = Instant.now();

    @NotBlank
    private String orderStatus;

    @NotBlank
    private List<Product> products = new ArrayList<>();
    
}
