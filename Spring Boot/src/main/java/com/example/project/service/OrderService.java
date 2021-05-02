package com.example.project.service;

import com.example.project.entity.Category;
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

    public List<Order> update(Order order) {
        var updatedOrder = orderRepository.findById(order.getId());

        if (updatedOrder.isPresent()) {
            var updatedOrder_ = updatedOrder.get();

            updatedOrder_.setDate_of_create(order.getDate_of_create() != null
                    ? order.getDate_of_create() : updatedOrder_.getDate_of_create());

            updatedOrder_.setDate_of_ready(order.getDate_of_ready() != null
                    ? order.getDate_of_ready() : updatedOrder_.getDate_of_ready());

            updatedOrder_.setAmount(order.getAmount() != null
                    ? order.getAmount() : updatedOrder_.getAmount());

            updatedOrder_.setProduct_id(order.getProduct_id() != null
                    ? order.getProduct_id() : updatedOrder_.getProduct_id());

            orderRepository.save(updatedOrder_);
        }
        return orderRepository.findAll();
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }
}
