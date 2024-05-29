package com.example.dinhakids.sistemaweb.Repositorio;

import com.example.dinhakids.sistemaweb.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository   <Product, String> {

    boolean existsByName(String name);

    boolean existsByIdAndName(String id, String name);

    Optional<Product> findByName(String name);
}

