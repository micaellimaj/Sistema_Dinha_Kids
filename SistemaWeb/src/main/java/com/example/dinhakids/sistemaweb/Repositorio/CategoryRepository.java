package com.example.dinhakids.sistemaweb.Repositorio;

import com.example.dinhakids.sistemaweb.Domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    boolean existsByName(String name);

    boolean existsByIdAndName(String id, String name);

    Optional<Category> findByName(String name);
}
