package com.example.dinhakids.sistemaweb.Repository;

import com.example.dinhakids.sistemaweb.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    boolean existsByIdAndName(int id, String name);

    Optional<Category> findByName(String name);

    boolean existsById(int id);

    Optional<Category> findById(int id);
}
