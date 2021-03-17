package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public void create(Person person){
            personRepository.save(person);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person find(Long id){
        return  personRepository.getOne(id);
    }
}
