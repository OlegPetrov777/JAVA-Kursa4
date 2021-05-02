package com.example.project.controller;

import com.example.project.entity.Category;
import com.example.project.entity.Order;
import com.example.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/api/order")
    public ResponseEntity<?> create(@RequestBody Order order) {
        orderService.create(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/order")
    public ResponseEntity<?> findAll() {
        final List<Order> orderlList = orderService.findAll();

        return orderlList != null && !orderlList.isEmpty()
                ? new ResponseEntity<>(orderlList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/order/{id}")
    public ResponseEntity<Optional<Order>> find(@PathVariable(name = "id") Long id) {
        final Optional<Order> order = orderService.find(id);

        return order.isPresent()
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/order/{id}")
    public ResponseEntity<List<Order>> update(@RequestBody Order order) {
        final List<Order> orderList = orderService.update(order);

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/order/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final Optional<Order> order = orderService.find(id);
        orderService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
