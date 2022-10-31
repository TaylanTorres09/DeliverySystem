package com.projeto.crud.springbootjpa.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projeto.crud.springbootjpa.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
}
