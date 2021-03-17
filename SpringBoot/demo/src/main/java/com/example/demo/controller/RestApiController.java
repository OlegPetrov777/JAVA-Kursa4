package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

public class RestApiController {


    private final PersonService personService;


    @Autowired
    public RestApiController(PersonService personService){
        this.personService = personService;
    }


    @PostMapping(value = "/api/persons")
    public ResponseEntity<?> create(@ResponseBody Person person){
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/api/person")
    public ResponseEntity<List<Person>> findAll(){
        final List<Person> personList = personService.findAll();

        return  personList != null && !personList.isEmpty()
                ? new ResponseEntity<>(personList, HttpStatus.OK)
                : new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
    }
}
