package com.example.dinhakids.sistemaweb.Controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.dinhakids.sistemaweb.Models.UserModel;
import com.example.dinhakids.sistemaweb.Repositorio.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername()); //procura se ja existe um username igual ao digitado

        if(user != null){ //se ja tiver um username igual ao digitado, dará erro bad request
            System.out.println("Usuário existente");
            return ResponseEntity.status(400).body("Usuário já existe");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12,userModel.getSenha().toCharArray()); // encriptador de senha
        userModel.setSenha(passwordHashred);

        var userCreated = this.userRepository.save(userModel); // cria o usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
