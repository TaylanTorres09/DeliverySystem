package com.projeto.crud.springbootjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.crud.springbootjpa.models.Category;
import com.projeto.crud.springbootjpa.models.Product;
import com.projeto.crud.springbootjpa.repositories.CategoryRepository;
import com.projeto.crud.springbootjpa.repositories.ProductRepository;
import com.projeto.crud.springbootjpa.services.exceptions.DatabaseException;
import com.projeto.crud.springbootjpa.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.get();
    }

    public ResponseEntity<?> registerCategory(Category category) {
        return new ResponseEntity<Category>(categoryRepository.save(category), HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateCategory(Category category, Long id) {
        try {
            Category cat = categoryRepository.getReferenceById(id);
            cat.setName(category.getName());
    
            return new ResponseEntity<Category>(categoryRepository.save(cat), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public ResponseEntity<String> deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);

            return new ResponseEntity<String>("Categoria " + id + "° removida", HttpStatus.OK);   
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (Exception e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
