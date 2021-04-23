package com.example.project.service;

import com.example.project.entity.Model;
import com.example.project.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    public void create(Model model){
        modelRepository.save(model);
    }

    public List<Model> findAll(){
        return modelRepository.findAll();
    }

    public Optional<Model> find(Long id){
        return modelRepository.findById(id);
    }

    public void delete(Long id){
        modelRepository.deleteById(id);
    }
}
