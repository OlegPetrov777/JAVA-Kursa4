package com.example.project.controller;

import com.example.project.entity.Category;
import com.example.project.entity.Company;
import com.example.project.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(value = "/api/company")
    public ResponseEntity<?> create(@RequestBody Company company) {
        companyService.create(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/company")
    public ResponseEntity<?> findAll() {
        final List<Company> companyList = companyService.findAll();

        return companyList != null && !companyList.isEmpty()
                ? new ResponseEntity<>(companyList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/company/{id}")
    public ResponseEntity<Optional<Company>> find(@PathVariable(name = "id") Long id) {
        final Optional<Company> company = companyService.find(id);

        return company.isPresent()
                ? new ResponseEntity<>(company, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/company/name_{name}")
    public ResponseEntity<List<Company>> findByName(@PathVariable(name = "name") String name){
        final List<Company> companyList = companyService.findByName(name);
        return companyList != null && !companyList.isEmpty()
                ? new ResponseEntity<>(companyList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/company/{id}")
    public ResponseEntity<List<Company>> update(@RequestBody Company company) {
        final List<Company> companyList = companyService.update(company);

        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/company/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final Optional<Company> company = companyService.find(id);
        companyService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
