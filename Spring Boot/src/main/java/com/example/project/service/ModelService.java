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


    public List<Model> findByName(String name){
        return modelRepository.findByName(name);
    }


    public List<Model> update(Model model) {
        var updatedModel = modelRepository.findById(model.getId());

        if (updatedModel.isPresent()) {
            var updatedModel_ = updatedModel.get();

            updatedModel_.setName(model.getName() != null
                    ? model.getName() : updatedModel_.getName());

            updatedModel_.setCategory(model.getCategory() != null
                    ? model.getCategory() : updatedModel_.getCategory());

            updatedModel_.setCompany(model.getCompany() != null
                    ? model.getCompany() : updatedModel_.getCompany());

            modelRepository.save(updatedModel_);
        }
        return modelRepository.findAll();
    }


    public void delete(Long id){
        modelRepository.deleteById(id);
    }
}
