package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class EmployeeService {
    @Autowired
    private EmployeeRepo repo;

    public List<Employee> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Employee employee) {
        repo.save(employee);
    }

    public Employee findById(Integer id) {
        return repo.findById(id).get();
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public List<Employee> findByKeyword(String keyword) {
        return repo.search(keyword);
    }
}

