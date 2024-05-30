package com.example.dinhakids.sistemaweb.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "categorias")
public class Category {

    @Id
    private String id;

    @Column(length = 45, nullable = false)
    private String name;
}
