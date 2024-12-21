package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface SupplyRepo extends JpaRepository<Supply, Integer> {
    @Query("SELECT p FROM Supply p WHERE CONCAT(p.supply_id, p.supplier_id, p.id, p.quantity, p.supply_date) LIKE %?1%")
    List<Supply> search(String keyword); // Указан правильный возвращаемый тип
}
