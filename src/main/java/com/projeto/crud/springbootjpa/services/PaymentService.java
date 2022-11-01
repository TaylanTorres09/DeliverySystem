package com.projeto.crud.springbootjpa.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.crud.springbootjpa.models.Payment;
import com.projeto.crud.springbootjpa.repositories.OrderRepository;
import com.projeto.crud.springbootjpa.repositories.PaymentRepository;
import com.projeto.crud.springbootjpa.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public ResponseEntity<?> registerOrder(Payment payment, String orderId) {
        payment.setMoment(Instant.now());
        Payment _payment = orderRepository.findById(orderId).map(order -> {
            payment.setOrder(order);
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException(orderId));
        return new ResponseEntity<>(_payment, HttpStatus.CREATED);
    }
    
}
