package com.projeto.crud.springbootjpa.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projeto.crud.springbootjpa.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    
}
