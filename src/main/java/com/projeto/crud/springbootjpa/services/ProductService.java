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
import com.projeto.crud.springbootjpa.repositories.ProductRepository;
import com.projeto.crud.springbootjpa.services.exceptions.DatabaseException;
import com.projeto.crud.springbootjpa.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    public ResponseEntity<?> registerProduct(Product product) {
        return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.CREATED);
    }

    public ResponseEntity<?> addCategoryProduct(Long categoryId, Long productId) {
        try {
            Category category = categoryService.findById(categoryId);

            Product product = productRepository.getReferenceById(productId);

            product.getCategories().add(category);

            return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateProduct(Product product, Long id) {
        try {
            Product prod = productRepository.getReferenceById(id);
            prod.setName(product.getName());
            prod.setDescription(product.getDescription());
            prod.setPrice(product.getPrice());
            prod.setImgUrl(product.getImgUrl());
    
            return new ResponseEntity<Product>(productRepository.save(prod), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);

            return new ResponseEntity<String>("Produto " + id + "° removido", HttpStatus.OK);   
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (Exception e) {
            throw new ResourceNotFoundException(id);
        }
    }


}
