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

    //cria novos usuarios
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateOrUpdateDTO dto){
        User user = userService.createUser(dto.getUser());

        return "usuarios/cadastro";
    }

    //retorna o usuario de acordo com o username
    @GetMapping(path = "/users/{id}") // retorna o usuario de acordo com o username
    public ResponseEntity<User> getUserByUsername(@PathVariable String username, RedirectAttributes attr) {
        User user = userService.getUserByUsername(username);

        userService.salvar(user);
        attr.addFlashAttribute("sucesso", "Usuario Salvo com sucesso!");
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

        userService.editar(user);
        attr.addFlashAttribute("sucesso", "Usuario Editado com sucesso!");
        return "usuarios/editar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") UUID id, RedirectAttributes attr) {
        userService.excluir(id);
        attr.addFlashAttribute("sucesso", "Usuario removido com sucesso!");
        return "usuarios/cadastrar";
    }

    @GetMapping("/buscar/nome")
    public String buscarPorNome(@RequestParam("nome") String nome, ModelMap model) {
        model.addAttribute("usuarios", userService.buscarPorNome(nome));
        return "usuarios/listar";
    }

    @GetMapping("/buscar/username")
    public String buscarPorUsername(@RequestParam("username") String username, ModelMap model) {
        model.addAttribute("usuarios", userService.buscarPorUsername(username));
        return "usuarios/listar";
    }

    @GetMapping("/buscar/email")
    public String buscarPorEmail(@RequestParam("email") String email, ModelMap model) {
        model.addAttribute("usuarios", userService.buscarPorEmail(email));
        return "usuarios/listar";
    }
}
