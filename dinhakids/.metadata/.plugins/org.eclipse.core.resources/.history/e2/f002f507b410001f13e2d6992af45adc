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
@RequestMapping(value = "/dinha/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    @PostMapping(path = "/novo")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductCreateOrUpdateDTO dto){
        Product product = productService.createProduct(dto.getProduct());

        return ResponseEntity.status(201).body(product);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        Product product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody @Valid ProductCreateOrUpdateDTO dto){
        Product product = dto.getProduct();
        product.setId(id);
        productService.updateProduct(product);

        return ResponseEntity.status(200).body(product);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
