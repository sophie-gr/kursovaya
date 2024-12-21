package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
    @Query("SELECT p FROM Supplier p WHERE CONCAT(p.supplier_id, p.name, p.contact_info) LIKE %?1%")
    List<Supplier> search(String keyword); // Указан правильный возвращаемый тип
}
