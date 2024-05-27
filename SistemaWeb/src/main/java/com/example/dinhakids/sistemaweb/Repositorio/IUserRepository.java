package com.example.dinhakids.sistemaweb.Repositorio;

import com.example.dinhakids.sistemaweb.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;
import java.util.function.Function;

public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
