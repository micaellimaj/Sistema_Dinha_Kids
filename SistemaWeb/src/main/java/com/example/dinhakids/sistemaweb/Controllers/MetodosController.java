package com.example.dinhakids.sistemaweb.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dinha/")
public class MetodosController {
    @GetMapping("/metodos")
    public String get(){
        return "Metodo get";
    }

    @PostMapping("/metodos")
    public String post(){
        return "Metodo post";
    }

    @PutMapping("/metodos")
    public String put(){
        return "Metodo put";
    }

    @DeleteMapping("/metodos")
    public String delete(){
        return "Metodo delete";
    }
}
