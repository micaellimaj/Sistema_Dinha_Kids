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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/cadastrar")
    public String Cadastrar(@Valid User user) {
        return "usuarios/cadastro";
    }

    @GetMapping("/listar")
    public String Listar(ModelMap model) {
        model.addAttribute("usuarios", userService.buscarTodos());
        return "usuarios/listar";
    }

    @PostMapping("/salvar")
    public String Salvar(@Valid User user, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "usuarios/cadastro";
        }

        userService.salvar(user);
        attr.addFlashAttribute("sucesso", "Usuario Salvo com sucesso!");
        return "usuarios/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") UUID id, ModelMap model) {
        model.addAttribute("usuario", userService.buscarPorId(id));
        return "usuarios/editar";
    }

    @PostMapping("/editar")
    public String editar(@Valid User user, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "usuarios/editar";
        }

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
