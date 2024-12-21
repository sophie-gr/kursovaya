package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Query("SELECT p FROM Employee p WHERE CONCAT(p.employee_id, p.employee_name, p.date_of_birth, p.email, p.phone_number, p.hire_date, p.job_title, p.department, p.salary) LIKE %?1%")
    List<Employee> search(String keyword); // Указан правильный возвращаемый тип
}
