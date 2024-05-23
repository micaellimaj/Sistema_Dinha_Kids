package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.Controllers.DTO.UserCreateOrUpdateDTO;
import com.example.dinhakids.sistemaweb.Models.User;
import com.example.dinhakids.sistemaweb.services.ProductService;
import com.example.dinhakids.sistemaweb.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dinha")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    //retorna todos os usuarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();

        return ResponseEntity.ok(users);
    }

    //cria novos usuarios
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateOrUpdateDTO dto){
        User user = userService.createUser(dto.getUser());

        return ResponseEntity.status(201).body(user);
    }

    //retorna o usuario de acordo com o username
    @GetMapping(path = "/users/{id}") // retorna o usuario de acordo com o username
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        return ResponseEntity.ok(user);
    }

    //atualiza o usuario
    @PutMapping(path = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String username, String email, @RequestBody @Valid UserCreateOrUpdateDTO dto) {
        User user = dto.getUser();
        user.setUsername(username);
        user.setEmail(email);
        userService.updateUser(user);

        return ResponseEntity.ok(user);
    }

    //deleta o usuario
    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);

        return ResponseEntity.noContent().build();
    }
}
