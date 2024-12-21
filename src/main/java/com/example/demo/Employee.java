package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // Required by JPA for entity instantiation
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id; // Приводим название поля к общепринятому стилю
    private String employee_name;
    private String date_of_birth;
    private String email;
    private String phone_number;
    private String hire_date;
    private String job_title;
    private String department;
    private Integer salary;

    public String getEmployeeName() {
        return employee_name;  // Вы можете добавить дополнительную логику, если нужно
    }
    public String getHireDate() {
        return hire_date;  // Тоже можно добавить логику для преобразования или форматирования
    }
    public Integer getSalary() {
        return salary;  // Тоже можно добавить логику для преобразования или форматирования
    }
}
