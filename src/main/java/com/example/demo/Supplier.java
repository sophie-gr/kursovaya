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
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplier_id; // Приводим название поля к общепринятому стилю
    private String name;
    private String contact_info;

    // Метод для получения контактной информации
    public String getContactInfo() {
        return contact_info;
    }
}
