package com.example.dinhakids.sistemaweb.Repositorio;

import com.example.dinhakids.sistemaweb.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository   <Product, String> {

    public Optional<Product> findProductByName(String name);
    public Optional<Product> findProductById(String Id);
}

