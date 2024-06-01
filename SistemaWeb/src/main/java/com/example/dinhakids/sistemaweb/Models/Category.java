package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity(name = "categorias")
public class Category {

    @Id
    private int id;

    @Column(length = 45, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
