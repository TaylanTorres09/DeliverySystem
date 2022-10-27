package com.projeto.crud.springbootjpa.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.crud.springbootjpa.dto.OrderDto;
import com.projeto.crud.springbootjpa.models.Order;
import com.projeto.crud.springbootjpa.models.enums.OrderStatus;
import com.projeto.crud.springbootjpa.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping("/register/{clientId}")
    public ResponseEntity<?> registerOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable(name = "clientId") Long clientId) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));
        return orderService.registerOrder(order, clientId);
    }

}
