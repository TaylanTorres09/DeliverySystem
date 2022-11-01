package com.projeto.crud.springbootjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.crud.springbootjpa.models.Payment;
import com.projeto.crud.springbootjpa.services.PaymentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @PostMapping("/register/{orderId}")
    public ResponseEntity<?> registerOrder(@PathVariable(name = "orderId") String clientId) {
        return paymentService.registerOrder(new Payment(), clientId);
    }

}
