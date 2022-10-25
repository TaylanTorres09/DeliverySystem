package com.projeto.crud.springbootjpa.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.crud.springbootjpa.dto.ProductDto;
import com.projeto.crud.springbootjpa.models.Product;
import com.projeto.crud.springbootjpa.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<FieldError>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        return productService.registerProduct(product);
    }

    @PutMapping("/category/{categoryId}/{productId}")
    public ResponseEntity<?> addCategoryProduct(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "productId") Long productId) {
        return productService.addCategoryProduct(categoryId, productId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody ProductDto productDto, @PathVariable String id) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        Long longId = Long.parseLong(id);
        return productService.updateProduct(product, longId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
