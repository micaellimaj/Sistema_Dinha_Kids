package com.example.dinhakids.sistemaweb.Repository;

import com.example.dinhakids.sistemaweb.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository  <User, String> {
    public Optional<User> findByUserName(String userName);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByName(String name);

    User getUserByName(String userName);
}
