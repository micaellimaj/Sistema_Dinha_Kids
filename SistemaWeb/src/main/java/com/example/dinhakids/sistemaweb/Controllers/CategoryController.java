package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.*;
import com.example.dinhakids.sistemaweb.Domain.Category;
import com.example.dinhakids.sistemaweb.Repositorio.CategoryRepository;
import com.example.dinhakids.sistemaweb.Services.CategoryService;
import com.example.dinhakids.sistemaweb.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;


    //retorna todas as categorias
    @GetMapping
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> Category = categoryService.getCategorys();

        return ResponseEntity.ok(new ArrayList<>(Category));
    }


    //retorna as categorias pelo nome
    @GetMapping(path = "/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name){
        Category category = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(category);
    }

    //cria novas categorias
    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryCreateDTO dto){
        Category category = new Category();

        category.setId(dto.getId());
        category.setName(dto.getName());


        categoryRepository.save(category);

        return ResponseEntity.ok(category);
    }

    //atualiza as categorias
    @PutMapping(path = "/{name}")
    public ResponseEntity<Category> updateCategory (@PathVariable String name, @RequestBody @Valid CategoryUpdateDTO dto){
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Categoria não encontrada"));

        dto.updateCategory(category);
        categoryRepository.save(category);

        return ResponseEntity.ok(category);
    }

    //deleta os produtos pelo id
    @DeleteMapping(path = "/{name}")
    public ResponseEntity<Category> deleteCategory(@PathVariable String name){
        categoryService.deleteCategory(name);
        return ResponseEntity.noContent().build();
    }



}
