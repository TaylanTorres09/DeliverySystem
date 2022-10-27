package com.projeto.crud.springbootjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.crud.springbootjpa.models.Order;
import com.projeto.crud.springbootjpa.repositories.OrderRepository;
import com.projeto.crud.springbootjpa.repositories.UserRepository;
import com.projeto.crud.springbootjpa.services.exceptions.DatabaseException;
import com.projeto.crud.springbootjpa.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    public ResponseEntity<?> registerOrder(Order order, Long clientId) {
        Order _order = userRepository.findById(clientId).map(user -> {
            order.setClient(user);
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException(clientId));

        return new ResponseEntity<>(_order, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);

            return new ResponseEntity<String>("Usuário " + id + "° removido", HttpStatus.OK);   
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
}
