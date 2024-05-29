package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.UserCreateDTO;
import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.UserUpdateDTO;
import com.example.dinhakids.sistemaweb.Domain.User;
import com.example.dinhakids.sistemaweb.Repositorio.UserRepository;
import com.example.dinhakids.sistemaweb.Services.PasswordEncoderService;
import com.example.dinhakids.sistemaweb.Services.UserService;
import com.example.dinhakids.sistemaweb.exceptions.UsernameNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dinha")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderService passwordEncoderService;

    //retorna todos os usuarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //cria novos usuarios
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<User> createUser(@RequestBody UserCreateDTO dto){
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        dto.setPasswordEncoderService(passwordEncoderService);
        dto.encodePassword();

        user.setPassword(dto.getPassword());
        userRepository.save(user);

        return ResponseEntity.status(201).body(user);
    }

    //retorna o usuario de acordo com o username
    @GetMapping(path = "/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        return ResponseEntity.ok(user);
    }


    //atualiza o usuario
    @PutMapping(path = "/users/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody @Valid UserUpdateDTO dto, UserService userService, PasswordEncoderService passwordEncoderService) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

        dto.updateUser(user, userService, passwordEncoderService);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    //deleta o usuario
    @DeleteMapping(path = "/users/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);

        return ResponseEntity.noContent().build();
    }

}
