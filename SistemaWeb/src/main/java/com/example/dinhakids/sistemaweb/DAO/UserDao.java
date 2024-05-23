package com.example.dinhakids.sistemaweb.DAO;

import com.example.dinhakids.sistemaweb.Domain.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    void save(User user);

    void update(User user);

    void delete(String id);

    User findById(UUID id);

    List<User> findAll();

    List<User> findByNome(String nome);

    List<User> findByUsername(String Username);

    List<User> findByEmail(String email);
}
