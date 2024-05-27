package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.UserCreateOrUpdateDTO;
import com.example.dinhakids.sistemaweb.Domain.User;
import com.example.dinhakids.sistemaweb.Services.ProductService;
import com.example.dinhakids.sistemaweb.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.DataTruncation;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dinha")
public class UserController {

    @Autowired
    private UserService userService;

    //retorna todos os usuarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();

        return "usuarios/listar";
    }

    // PAGE CADASTRAR       ----------------------------------
    // Responsável por pegar os dados do form definido na página cadastrar, encontrado pelo nome "dashboard"
    @PostMapping("/dashboard")
    public String handleRegistrationForm(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            // Trate os erros de validação aqui
            return "cadastrar"; // Retorna a mesma página para mostrar mensagens de erro
        }
        // Processa o usuário aqui (por exemplo, salva no banco de dados)
        userService.save(user); // Supondo que você tenha um método save no UserService
        return "redirect:/dashboard"; // Redireciona para uma página de sucesso
    }

    //cria novos usuarios
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateOrUpdateDTO dto){
        User user = userService.createUser(dto.getUser());

        return "usuarios/cadastro";
    }

    //retorna o usuario de acordo com o username
    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        return "usuarios/cadastrar";
    }

    //retorna o usuario de acordo com o nome
    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUserByName(@PathVariable String nome) {
        User user = userService.getUserByName(nome);

        return "usuarios/cadastrar";
    }



    //atualiza o usuario
    @PostMapping(path = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String username, String email, @RequestBody @Valid UserCreateOrUpdateDTO dto) {
        User user = dto.getUser();
        user.setUsername(username);
        user.setEmail(email);
        userService.updateUser(user);

        return "usuarios/editar";
    }

    //deleta o usuario
    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);

        return "usuarios/cadastrar";
    }

}
