package com.projeto.crud.springbootjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.crud.springbootjpa.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
