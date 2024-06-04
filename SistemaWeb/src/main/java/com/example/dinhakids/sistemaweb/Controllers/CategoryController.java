package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.*;
import com.example.dinhakids.sistemaweb.Models.Category;
import com.example.dinhakids.sistemaweb.Repository.CategoryRepository;
import com.example.dinhakids.sistemaweb.Services.CategoryService;
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
        Category category = dto.createCategory(new Category());
        category = categoryService.createCategory(category);
        return ResponseEntity.ok(category);
    }

    //atualiza as categorias
    @PutMapping(path = "/{name}")
    public ResponseEntity<Category> updateCategory (@PathVariable String name, @RequestBody @Valid CategoryUpdateDTO dto){
        Category category = categoryService.updateCategory(dto, name);

        return ResponseEntity.ok(category);
    }

    //deleta os produtos pelo id
    @DeleteMapping(path = "/{name}")
    public ResponseEntity<Category> deleteCategory(@PathVariable String name){
        categoryService.deleteCategory(name);
        return ResponseEntity.noContent().build();
    }



}
