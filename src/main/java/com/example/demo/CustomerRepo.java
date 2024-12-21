package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    @Query("SELECT p FROM Customer p WHERE CONCAT(p.customer_id, p.email, p.name, p.phone) LIKE %?1%")
    List<Customer> search(String keyword); // Указан правильный возвращаемый тип
}
