package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor // Required by JPA for entity instantiation
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supply_id; // Приводим название поля к общепринятому стилю
    private Integer supplier_id;
    private Integer id;
    private Integer quantity;
    private String supply_date;

    public LocalDate getSupplyDate() {
        try {
            // List of potential date formats
            String[] formats = {
                    "dd-MM-yyyy",
                    "yyyy-MM-dd",
                    "dd.MM.yyyy",
                    "MM/dd/yyyy",
                    "MMMM d, yyyy"
            };

            for (String format : formats) {
                try {
                    return LocalDate.parse(supply_date, DateTimeFormatter.ofPattern(format));
                } catch (Exception ignored) {}
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
