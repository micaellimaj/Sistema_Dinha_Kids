package com.example.dinhakids.sistemaweb.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/novo")
    public String login() {
        // Lógica de login aqui
        return ("ok");
    }
}