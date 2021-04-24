package com.example.project.controller;

import com.example.project.entity.Customer;
import com.example.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/api/customer")
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        customerService.create(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/customer")
    public ResponseEntity<?> findAll() {
        final List<Customer> customerList = customerService.findAll();

        return customerList != null && !customerList.isEmpty()
                ? new ResponseEntity<>(customerList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/customer/{id}")
    public ResponseEntity<Optional<Customer>> find(@PathVariable(name = "id") Long id) {
        final Optional<Customer> customer = customerService.find(id);

        return customer.isPresent()
                ? new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/api/customer/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final Optional<Customer> customer = customerService.find(id);
        customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
