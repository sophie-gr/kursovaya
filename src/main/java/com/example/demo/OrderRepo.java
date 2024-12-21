package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query("SELECT p FROM Order p WHERE CONCAT(p.order_id, p.customer_id, p.order_date, p.total_amount, p.status) LIKE %?1%")
    List<Order> search(String keyword); // Указан правильный возвращаемый тип
}
