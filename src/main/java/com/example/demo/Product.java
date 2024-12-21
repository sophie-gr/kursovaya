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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Приводим название поля к общепринятому стилю
    private String name;
    private String description;
    private float price;
    private Integer categoryId;
    private String categoryName;
    private Integer quantity;
}
