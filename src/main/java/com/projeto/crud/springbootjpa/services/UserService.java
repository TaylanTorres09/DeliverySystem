package com.projeto.crud.springbootjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.crud.springbootjpa.models.User;
import com.projeto.crud.springbootjpa.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public ResponseEntity<?> registerUser(User user) {
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteUser(Long id) {
        userRepository.deleteById(id);

        return new ResponseEntity<String>("Usuário " + id + "° removido", HttpStatus.OK);
    }

}
