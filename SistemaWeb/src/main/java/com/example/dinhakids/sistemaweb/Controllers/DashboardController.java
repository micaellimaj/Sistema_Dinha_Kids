package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.Models.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public void dashboard() {
        // Lógica do dashboard aqui
    }

    @PostMapping("/salvausuario")
    public void salvaUsuario(@ModelAttribute UserModel usuario) {
        // Lógica para validar e salvar o usuário no banco
        System.out.println(usuario.getNome());
        // Você pode chamar serviços ou métodos para validar e salvar o usuário
        // Redirecionar para o dashboard
    }
}