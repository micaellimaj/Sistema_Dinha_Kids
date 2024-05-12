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


    //verifica e cria novos produtos
    @Transactional(rollbackOn = Exception.class)
    public Product createProduct(Product product) {
        Optional<Product> NameExists = productRepository.findProductByNome(product.getNome());
        if(NameExists.isPresent()){
            throw new BusinessException("Já existe um produto com o Nome informado");
        }

        Optional<Product> IdExists = productRepository.findProductById(product.getId());
        if(IdExists.isPresent()){
            throw new BusinessException("Já existe um produto com o ID informado");
        }

        productRepository.save(product);
        return product;
    }


    //procura o produto pelo id
    public Product getProductById(String id) {
        Optional<Product> productExists = productRepository.findById(id);

        return productExists.orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    //atualiza o produto
    @Transactional(rollbackOn = Exception.class)
    public Product updateProduct(Product product) {
        Optional<Product> idExists = productRepository.findProductByNome(product.getNome());
        if(idExists.isPresent() && !idExists.get().getId().equals(product.getId())){
            throw new BusinessException("Já existe um produto com o id informado");
        }

        Optional<Product> NameExists = productRepository.findProductByNome(product.getNome());
        if(NameExists.isPresent() && !NameExists.get().getId().equals(product.getId())){
            throw new BusinessException("Já existe um produto com o nome informado");
        }

        productRepository.save(product);
        return product;
    }

    //deleta o produto pelo id
    public void deleteProduct(String id) {
        Optional<Product> productExists = productRepository.findById(id);

        Product product= productExists.orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        productRepository.delete(product);
    }

}


