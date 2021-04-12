package com.example.project.service;

import com.example.project.entity.Customer;
import com.example.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void create(Customer manufacturer){
        customerRepository.save(manufacturer);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Optional<Customer> find(Long id){
        return customerRepository.findById(id);
    }

    public void delete(Long id){
        customerRepository.deleteById(id);
    }
}
