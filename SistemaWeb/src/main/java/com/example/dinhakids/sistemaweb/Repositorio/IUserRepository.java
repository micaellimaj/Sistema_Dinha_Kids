package com.example.dinhakids.sistemaweb.Repositorio;

import com.example.dinhakids.sistemaweb.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;
import java.util.function.Function;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
