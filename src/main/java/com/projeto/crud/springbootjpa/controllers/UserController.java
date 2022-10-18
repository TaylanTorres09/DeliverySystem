package com.projeto.crud.springbootjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.crud.springbootjpa.models.User;
import com.projeto.crud.springbootjpa.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    public List<User> findAll() {
        return userService.findAll();
    }

}
