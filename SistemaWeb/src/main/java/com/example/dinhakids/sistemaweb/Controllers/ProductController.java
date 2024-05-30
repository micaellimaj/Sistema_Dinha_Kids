package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductCreateDTO;
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
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    //retorna os produtos pelo nome
    @GetMapping(path = "/nome/{name}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable String name){
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    //cria novos produtos
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductCreateDTO dto){
        Product product = new Product();

        product.setName(dto.getName());
        product.setId(dto.getId());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        productRepository.save(product);

        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    //atualiza os produtos
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable int id, @RequestBody @Valid ProductUpdateDTO dto){
        Product product = productRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        dto.updateProduct(product);
        productRepository.save(product);

        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    //deleta os produtos pelo id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}
