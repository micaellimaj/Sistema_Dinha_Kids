package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductResponseDTO;
import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductUpdateDTO;
import com.example.dinhakids.sistemaweb.Domain.Product;
import com.example.dinhakids.sistemaweb.Repositorio.ProductRepository;
import com.example.dinhakids.sistemaweb.Services.ProductService;
import com.example.dinhakids.sistemaweb.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;


    //retorna todos os produtos
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProduct(){
        List<Product> products = productService.getProducts();

        return ResponseEntity.ok(products.stream()
                .map(ProductResponseDTO ::new)
                .collect(Collectors.toList()));
    }

    //retorna os produtos pelo id
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    //retorna os produtos pelo nome
    @GetMapping(path = "/{name}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable String name){
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    //atualiza os produtos
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @RequestBody @Valid ProductUpdateDTO dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        dto.updateProduct(product);
        productRepository.save(product);

        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    //deleta os produtos pelo id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}
