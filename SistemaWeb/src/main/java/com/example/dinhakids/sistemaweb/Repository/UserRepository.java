package com.example.dinhakids.sistemaweb.Repository;

import com.example.dinhakids.sistemaweb.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository  <User, String> {
     Optional<User> findByUserName(String userName);
     Optional<User> findByEmail(String email);
     Optional<User> findByName(String name);

     User getUserByName(String userName);

     Optional<User> findByLogin(String login);
}
