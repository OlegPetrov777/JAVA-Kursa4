package com.example.project.service;

import com.example.project.entity.Order;
import com.example.project.entity.Product;
import com.example.project.repository.OrderRepository;
import com.example.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public void create(Order order){
        Product product = productRepository.findById(order.getProduct_id()).orElseThrow();
        order.setProduct(product);
        orderRepository.save(order);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> find(Long id){
        return orderRepository.findById(id);
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }
}
