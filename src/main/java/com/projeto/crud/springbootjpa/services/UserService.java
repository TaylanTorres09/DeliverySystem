package com.projeto.crud.springbootjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.crud.springbootjpa.dto.LoginDto;
import com.projeto.crud.springbootjpa.models.User;
import com.projeto.crud.springbootjpa.repositories.UserRepository;
import com.projeto.crud.springbootjpa.services.exceptions.DatabaseException;
import com.projeto.crud.springbootjpa.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public ResponseEntity<?> registerUser(User user) {
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<?> loginUser(LoginDto login) {
        List<User> users = userRepository.findAll();
        for(User user: users) {
            if(login.getEmail().equals(user.getEmail()) && login.getPassword().equals(user.getPassword())) {
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Email ou senha incorretos", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteUser(String id) {
        try {
            userRepository.deleteById(id);

            return new ResponseEntity<String>("Usuário " + id + "° removido", HttpStatus.OK);   
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ResponseEntity<?> updateUser(String id, User updateUser) {
        try {
            User user = userRepository.findById(id).get();

            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            user.setPhone(updateUser.getPhone());
            user.setPassword(updateUser.getPassword());

            return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);   
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
