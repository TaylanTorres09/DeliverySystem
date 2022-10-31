package com.projeto.crud.springbootjpa.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.OneToMany;
import lombok.Data;

@Document(collection = "users")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;
    private String email;
    private String phone;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
    
}
