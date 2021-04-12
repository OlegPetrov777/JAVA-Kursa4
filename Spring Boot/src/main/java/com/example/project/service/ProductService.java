package com.example.project.service;

import com.example.project.entity.Product;
import com.example.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void create(Product manufacturer){
        productRepository.save(manufacturer);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> find(Long id){
        return productRepository.findById(id);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}
