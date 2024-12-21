package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor // Required by JPA for entity instantiation
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    private Integer customer_id; // Приводим название поля к общепринятому стилю
    private String order_date;
    private Integer total_amount;
    private String status;

    public LocalDate getOrderDate() {
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
                    return LocalDate.parse(order_date, DateTimeFormatter.ofPattern(format));
                } catch (Exception ignored) {}
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getTotalAmount() {
        return total_amount;
    }
}
