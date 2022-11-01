package com.projeto.crud.springbootjpa.models;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Document(collection = "payments")
@Data
public class Payment implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Instant moment;

    @OneToOne
    @MapsId
    private Order order;
}
