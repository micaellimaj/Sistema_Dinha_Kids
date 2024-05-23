package com.example.dinhakids.sistemaweb.DAO;

import com.example.dinhakids.sistemaweb.Domain.Product;

import java.util.List;

public interface ProductDao {

    void save(Product product);

    void update(Product product);

    void delete(String id);

    Product findById(String id);

    List<Product> findAll();

    List<Product> findByNome(String nome);

    List<Product> findByFornecedorId(String id);

}
