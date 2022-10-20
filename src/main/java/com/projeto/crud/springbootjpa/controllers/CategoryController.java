package com.projeto.crud.springbootjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.crud.springbootjpa.models.Category;
import com.projeto.crud.springbootjpa.services.CategoryService;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    public List<Category> findAll() {
        return categoryService.findAll();
    }

}
