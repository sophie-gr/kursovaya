package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ProductService {
    @Autowired
    private ProductRepo repo;
    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    public void save(Product product) {
        repo.save(product);
    }
    public Product findById(Integer id) {
        return repo.findById(id).get();
    }
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
