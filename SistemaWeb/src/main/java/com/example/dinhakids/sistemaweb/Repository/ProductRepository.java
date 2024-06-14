package com.example.dinhakids.sistemaweb.Repository;

import com.example.dinhakids.sistemaweb.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository   <Product, String> {

    boolean existsByName(String name);

    boolean existsByIdAndName(int id, String name);

    Optional<Product> findByName(String name);
    Optional<Product> findById(int id);

    boolean existsById(int id);

    boolean existsByNameAndId(String name, int id);
}

