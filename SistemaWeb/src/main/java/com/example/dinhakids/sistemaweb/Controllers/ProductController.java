package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductCreateDTO;
import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductResponseDTO;
import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductUpdateDTO;
import com.example.dinhakids.sistemaweb.Models.Category;
import com.example.dinhakids.sistemaweb.Models.Product;
import com.example.dinhakids.sistemaweb.Repository.CategoryRepository;
import com.example.dinhakids.sistemaweb.Repository.ProductRepository;
import com.example.dinhakids.sistemaweb.Services.CategoryService;
import com.example.dinhakids.sistemaweb.Services.ProductService;
import com.example.dinhakids.sistemaweb.exceptions.CategoryNotFoundException;
import com.example.dinhakids.sistemaweb.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/produtos/tables")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    // Retorna a página com a lista de produtos
    @GetMapping("/tabela")
    public String listarProdutos(Model model) {
        List<Product> produtos = productService.getProducts();
        model.addAttribute("produtos", produtos);  // Alterado para "produtos"
        return "tables"; // Assumindo que o arquivo do template é chamado tables.html
    }

    // Retorna os produtos pelo ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    // Retorna os produtos pelo nome
    @GetMapping(path = "/nome/{name}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable String name){
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    // Cria novos produtos
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<ProductResponseDTO> createProduct(@ModelAttribute ProductCreateDTO dto, Model model){
        Product product = productService.createProduct(dto);
        productRepository.save(product);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    // Atualiza os produtos
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable int id, @ModelAttribute @Valid ProductUpdateDTO dto, Model model){
        Product product = productService.updateProduct(dto, id);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    // Deleta os produtos pelo ID
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

