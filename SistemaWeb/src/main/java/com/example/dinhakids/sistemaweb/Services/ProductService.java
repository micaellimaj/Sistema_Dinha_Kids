package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.Domain.Product;
import com.example.dinhakids.sistemaweb.Repositorio.ProductRepository;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

            if(productRepository.existsById(product.getId())) {
                throw new BusinessException("Já existe um produto com a Referência informada");
            }

            if(productRepository.existsByName(product.getName())){
                throw new BusinessException("Já existe um produto com o nome informado");
            }

            productRepository.save(product);
            return product;
        }


        //procura o produto pelo id
        public Product getProductById(String id) {
            return productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        }

        //procura o produto pelo nome
         public Product getProductByName(String name) {
             return productRepository.findByName(name)
                     .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
         }

        //atualiza o produto
        @Transactional(rollbackOn = Exception.class)
        public Product updateProduct(Product product) {

            if(productRepository.existsByIdAndName(product.getId(), product.getName()) &&
                !productRepository.findById(product.getId()).get().equals(product)){
                throw new BusinessException("Já existe um produto com a referência e o nome informados");
            }

            productRepository.save(product);
            return product;
        }

        //deleta o produto pelo id
        public void deleteProduct(String id) {

            Product product= productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
            productRepository.delete(product);
        }

}


