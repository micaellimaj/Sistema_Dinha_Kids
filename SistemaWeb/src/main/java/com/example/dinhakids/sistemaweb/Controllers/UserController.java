package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.UserCreateDTO;
import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.UserUpdateDTO;
import com.example.dinhakids.sistemaweb.Models.User;
import com.example.dinhakids.sistemaweb.Repository.UserRepository;
import com.example.dinhakids.sistemaweb.Services.PasswordEncoderService;
import com.example.dinhakids.sistemaweb.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/dinha/cadastro") // @RequestMapping("/dinha") estou mudando para fazer conexão com o html
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderService passwordEncoderService;

    // Retorna a página de cadastro
    @GetMapping("/pagina")
    public String getAllUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "cadastro"; // Assumindo que o arquivo do template é chamado cadastro.html
    }
    // retorna todos os usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //cria novos usuarios
    @PostMapping("/pagina")
    public String createUser(@ModelAttribute @Valid UserCreateDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cadastro"; // Retorna a página de cadastro com erros
        }
        User user = userService.createUser(dto);
        return "redirect:/dinha/cadastro/pagina"; // Redireciona para a página de cadastro após criar o usuário
    }

    //retorna o usuario de acordo com o username
    @GetMapping(path = "/usuarios/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        User user = userService.getUserByUsername(userName);

        return ResponseEntity.ok(user);
    }


    //atualiza o usuario
    @PutMapping(path = "/usuarios/{userName}")
    public ResponseEntity<User> updateUser(@PathVariable String userName, @ModelAttribute @Valid UserUpdateDTO dto, Model model) {
        User user = userService.updateUser(userName, dto);

        return ResponseEntity.ok(user);
    }

    //deleta o usuario
    @DeleteMapping(path = "/usuarios/{userName}")
    public ResponseEntity<User> deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);

        return ResponseEntity.noContent().build();
    }

}
