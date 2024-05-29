package com.example.dinhakids.sistemaweb.Domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Data
@Entity(name = "produtos")
public class Product {

    @Id
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "###.00")
    private double price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime lastUpdate;

}
