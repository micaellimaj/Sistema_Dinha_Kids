package com.example.dinhakids.sistemaweb.Controllers;


import com.example.dinhakids.sistemaweb.Models.Produtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dinha/")
public class ProdutosController {

    @GetMapping("/produtos")
    public ResponseEntity<Produtos> obterProdutos() {
        Produtos produtos = new Produtos(120, "vestido", 120, "qualquer", 150.00, 10, 5);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
}
