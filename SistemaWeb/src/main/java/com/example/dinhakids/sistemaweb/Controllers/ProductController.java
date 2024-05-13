package com.example.dinhakids.sistemaweb.Controllers;


import com.example.dinhakids.sistemaweb.Controllers.DTO.ProductCreateOrUpdateDTO;
import com.example.dinhakids.sistemaweb.Models.Product;
import com.example.dinhakids.sistemaweb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(value = "/dinha") //Fazer referência a table
public class ProductController {

    @Autowired
    private ProductService productService;


    //retorna todos os produtos
    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    //cria novos produtos caso as informações sejam validas
    @PostMapping(path = "/novo")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductCreateOrUpdateDTO dto){
        Product product = productService.createProduct(dto.getProduct());

        return ResponseEntity.status(201).body(product);
    }

    //retorna os produtos pelo id
    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        Product product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    //atualiza os produtos
    @PutMapping(path = "{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, String nome,int quantidade,double preco, @RequestBody @Valid ProductCreateOrUpdateDTO dto){
        Product product = dto.getProduct();
        product.setId(id);
        product.setNome(nome);
        product.setQuantidade(quantidade);
        product.setPreco(preco);
        productService.updateProduct(product);

        return ResponseEntity.status(200).body(product);
    }

    //deleta os produtos pelo id
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
