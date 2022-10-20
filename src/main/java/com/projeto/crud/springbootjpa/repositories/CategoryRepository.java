package com.projeto.crud.springbootjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.crud.springbootjpa.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
