package com.example.project.service;

import com.example.project.entity.Category;
import com.example.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void create(Category category){
        categoryRepository.save(category);
    }


    public List<Category> findAll(){
        return categoryRepository.findAll();
    }


    public Optional<Category> find(Long id){
        return categoryRepository.findById(id);
    }


    public List<Category> update(Category category) {
        var updatedCategory = categoryRepository.findById(category.getId());

        if (updatedCategory.isPresent()) {
            var updatedCategory_ = updatedCategory.get();

            updatedCategory_.setName(category.getName() != null
                    ? category.getName() : updatedCategory_.getName());

            categoryRepository.save(updatedCategory_);
        }
        return categoryRepository.findAll();
    }


    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
