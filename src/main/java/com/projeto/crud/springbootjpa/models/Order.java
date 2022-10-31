package com.projeto.crud.springbootjpa.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.crud.springbootjpa.models.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Document(collection = "orders")
@Data
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private List<Product> product;

    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
    
    public Double getTotal() {
        Double maxValue = !product.isEmpty() ? product.get(0).getPrice() : 0;
        for(Product prod: product) {
            maxValue = maxValue >= prod.getPrice() ? maxValue : prod.getPrice();
        }
        return maxValue;
    }

}
