package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo repo;

    public List<Customer> listAll() {
        return repo.findAll();
    }

    public List<Customer> listAll(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Customer customer) {
        repo.save(customer);
    }

    public Customer findById(Integer id) {
        return repo.findById(id).get();
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public List<Customer> findByKeyword(String keyword) {
        return repo.search(keyword);
    }
}