package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SupplyService {
    @Autowired
    private SupplyRepo repo;
    public List<Supply> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    public void save(Supply supply) {
        repo.save(supply);
    }
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
    public Supply findByID(Integer id) {
        return repo.findById(id).orElse(null);  // Если объект с таким id не найден, возвращается null.
    }

    public List<Supply> findByKeyword(String keyword) {
        return repo.search(keyword);
    }
}
