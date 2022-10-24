package com.projeto.crud.springbootjpa.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.crud.springbootjpa.dto.CategoryDto;
import com.projeto.crud.springbootjpa.models.Category;
import com.projeto.crud.springbootjpa.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<FieldError>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        return categoryService.registerCategory(category);
    }

}
