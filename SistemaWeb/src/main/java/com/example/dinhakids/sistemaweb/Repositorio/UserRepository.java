package com.example.dinhakids.sistemaweb.Repositorio;

import com.example.dinhakids.sistemaweb.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository  <User, String> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
