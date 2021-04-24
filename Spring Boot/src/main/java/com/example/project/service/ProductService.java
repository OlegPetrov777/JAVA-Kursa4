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

    public void create(Product product){
        productRepository.save(product);
    }


    public List<Product> findAll(){
        return productRepository.findAll();
    }


    public Optional<Product> find(Long id){
        return productRepository.findById(id);
    }


    public List<Product> update(Product product) {
        var updatedProduct = productRepository.findById(product.getId());

        if (updatedProduct.isPresent()) {
            var updatedProduct_ = updatedProduct.get();

            updatedProduct_.setPrice(product.getPrice() != null
                    ? product.getPrice() : updatedProduct_.getPrice());

            updatedProduct_.setColor(product.getColor() != null
                    ? product.getColor() : updatedProduct_.getColor());

            updatedProduct_.setCount(product.getCount() != null
                    ? product.getCount() : updatedProduct_.getCount());

            updatedProduct_.setModel(product.getModel() != null
                    ? product.getModel() : updatedProduct_.getModel());

            productRepository.save(updatedProduct_);
        }
        return productRepository.findAll();
    }


    public void delete(Long id){
        productRepository.deleteById(id);
    }
}
