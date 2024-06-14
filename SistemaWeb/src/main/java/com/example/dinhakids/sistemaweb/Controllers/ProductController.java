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
@RequestMapping("/produtos/tables") //@RequestMapping("/produtos") Alterei para fazer ligação com o html
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;


    //retorna  a página
    @GetMapping("/tabela")
    public String listarProdutos(Model model) {
        List<Product> produtos = productService.getProducts();
        model.addAttribute("tabela", produtos);
        return "tables"; // Assumindo que o arquivo do template é chamado listaProdutos.html
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
    public ResponseEntity<ProductResponseDTO> createProduct(@ModelAttribute ProductCreateDTO dto, Model model){

        Product product = productService.createProduct(dto);
        productRepository.save(product);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }


    //atualiza os produtos
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable int id, @ModelAttribute @Valid ProductUpdateDTO dto, Model model){
        Product product = productService.updateProduct(dto, id);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    //deleta os produtos pelo id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}

