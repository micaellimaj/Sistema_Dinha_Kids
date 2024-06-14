package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.CategoryUpdateDTO;
import com.example.dinhakids.sistemaweb.Models.Category;
import com.example.dinhakids.sistemaweb.Repository.CategoryRepository;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.CategoryNotFoundException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategorys(){
        return categoryRepository.findAll();
    }


    //verifica e cria novas categorias
    @Transactional(rollbackOn = Exception.class)
    public Category createCategory(Category category) {

        if(categoryRepository.existsById(category.getId())) {
            throw new BusinessException("Já existe uma categoria com o id informado");
        }

        if(categoryRepository.existsByName(category.getName())){
            throw new BusinessException("Já existe uma categoria com o nome informado");
        }

        categoryRepository.save(category);
        return category;
    }


    //procura a categoria pelo nome
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
    }

    //atualiza a categoria
    @Transactional(rollbackOn = Exception.class)
    public Category updateCategory(CategoryUpdateDTO categoryUpdateDTO, String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));

        if(categoryRepository.existsByIdAndName(category.getId(), category.getName()) &&
                !categoryRepository.findByName(category.getName()).get().equals(category)){
            throw new BusinessException("Já existe uma categoria com o id e o nome informados");
        }

        categoryUpdateDTO.updateCategory(category);
        return category;
    }

    //deleta a categoria pelo id
    public void deleteCategory(String name) {

        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
        categoryRepository.delete(category);
    }

}
