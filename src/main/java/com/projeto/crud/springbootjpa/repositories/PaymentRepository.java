package com.projeto.crud.springbootjpa.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projeto.crud.springbootjpa.models.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    
}
