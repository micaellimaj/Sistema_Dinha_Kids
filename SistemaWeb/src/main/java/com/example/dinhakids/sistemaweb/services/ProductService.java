package com.example.dinhakids.sistemaweb.services;

import com.example.dinhakids.sistemaweb.Models.Product;
import com.example.dinhakids.sistemaweb.Repositorio.ProductRepository;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    public Product createProduct(Product product) {
        Optional<Product> productExists = productRepository.findProductByNome(product.getNome());
        if(productExists.isPresent()){
            throw new BusinessException("Já existe um produto com o Nome informado");
        }

        productRepository.save(product);
        return product;
    }

    public Product getProductById(String id) {
        Optional<Product> productExists = productRepository.findById(id);

        return productExists.orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    @Transactional(rollbackOn = Exception.class)
    public Product updateProduct(Product product) {
        Optional<Product> productExists = productRepository.findProductByNome(product.getNome());
        if(productExists.isPresent() && !productExists.get().getId().equals(product.getId())){
            throw new BusinessException("Já existe um evento com o título informado");
        }

        productRepository.save(product);
        return product;
    }

    public void deleteProduct(String id) {
        Optional<Product> productExists = productRepository.findById(id);

        Product product= productExists.orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        productRepository.delete(product);
    }

}


