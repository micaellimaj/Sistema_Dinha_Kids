package com.example.dinhakids.sistemaweb.Controllers;


import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductCreateOrUpdateDTO;
import com.example.dinhakids.sistemaweb.Domain.Product;
import com.example.dinhakids.sistemaweb.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;


    //retorna todos os produtos
    @GetMapping("/produtos")
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    //retorna os produtos pelo id
    @GetMapping(path = "/produtos/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        Product product = productService.getProductById(id);
        return ResponseEntity.status(201).body(product);
    }

    //retorna os produtos pelo nome
    @GetMapping(path = "/produtos/nome")
    public ResponseEntity<Product> getProductByName(@PathVariable String nome){
        Product product = productService.getProductByName(nome);
        return ResponseEntity.status(201).body(product);
    }

    //atualiza os produtos
    @PutMapping(path = "/produtos/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, String nome,int quantidade,double preco, @RequestBody @Valid ProductCreateOrUpdateDTO dto){
        Product product = dto.getProduct();
        return ResponseEntity.status(201).body(product);
    }

    //deleta os produtos pelo id
    @GetMapping(path = "/produtos/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}
