package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE CONCAT(p.id, p.name, p.description, p.price, p.categoryId, p.categoryName, p.quantity) LIKE %?1%")
    List<Product> search(String keyword); // Указан правильный возвращаемый тип
}
