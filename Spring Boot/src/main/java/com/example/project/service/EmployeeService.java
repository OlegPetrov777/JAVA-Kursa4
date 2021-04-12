package com.example.project.service;

import com.example.project.entity.Employee;
import com.example.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void create(Employee manufacturer){
        employeeRepository.save(manufacturer);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> find(Long id){
        return employeeRepository.findById(id);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
