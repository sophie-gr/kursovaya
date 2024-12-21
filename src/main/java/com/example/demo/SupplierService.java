package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SupplierService {
    @Autowired
    private SupplierRepo repo;
    public List<Supplier> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    public void save(Supplier supplier) {
        repo.save(supplier);
    }
    public Supplier findByID(Integer id) {
        return repo.findById(id).get();
    }
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
    public List<Supplier> findByKeyword(String keyword) {
        return repo.search(keyword);
    }
}
