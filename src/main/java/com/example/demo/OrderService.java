package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo repo;

    public List<Order> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public List<Order> listAll() {
        return repo.findAll();
    }

    public void save(Order order) {
        repo.save(order);
    }

    public Order findById(Integer id) {
        return repo.findById(id).get();
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public List<Order> findByKeyword(String keyword) {
        return repo.search(keyword);
    }
}