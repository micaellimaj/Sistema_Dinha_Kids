package com.example.dinhakids.sistemaweb.Controllers;


import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductCreateOrUpdateDTO;
import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import com.example.dinhakids.sistemaweb.Domain.Product;
import com.example.dinhakids.sistemaweb.Services.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    //retorna todos os fornecedores
    @GetMapping("/fornecedores")
    public ResponseEntity<List<Fornecedor>> getFornecedor(){
        List<Fornecedor> fornecedores = fornecedorService.getFornecedores();

        return ResponseEntity.ok(fornecedores);
    }

    //retorna os fornecedores pelo id
    @GetMapping(path = "/fornecedores/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable String id){
        Fornecedor fornecedor = fornecedorService.getFornecedorById(id);
        return ResponseEntity.status(201).body(fornecedor);
    }

    //retorna os fornecedores pelo nome
    @GetMapping(path = "/fornecedores/nome")
    public ResponseEntity<Fornecedor> getFornecedorByName(@PathVariable String nome){
        Fornecedor fornecedor = fornecedorService.getFornecedorByName(nome);
        return ResponseEntity.status(201).body(fornecedor);
    }

    //atualiza os fornecedores
    //@PutMapping(path = "/fornecedores/{id}")
    //public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable String id, String nome, @RequestBody @Valid ProductCreateOrUpdateDTO dto){
        //Product product = dto.getProduct();
        //return ResponseEntity.status(201).body(product);
    //}


    //deleta os fornecedores
    //@GetMapping(path = "/produtos/{id}")
    //public ResponseEntity<Product> deleteProduct(@PathVariable String id){
        //productService.deleteProduct(id);
        //return ResponseEntity.noContent().build();
    //}
}
