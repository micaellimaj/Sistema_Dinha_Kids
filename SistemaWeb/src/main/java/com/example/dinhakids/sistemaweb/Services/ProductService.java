package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductUpdateDTO;
import com.example.dinhakids.sistemaweb.Models.Product;
import com.example.dinhakids.sistemaweb.Repository.CategoryRepository;
import com.example.dinhakids.sistemaweb.Repository.ProductRepository;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import com.example.dinhakids.sistemaweb.exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

        @Autowired
        private  ProductRepository productRepository;

        private CategoryRepository categoryRepository;

        public List<Product> getProducts(){
            return productRepository.findAll();
        }



        //procura o produto pelo id
        public Product getProductById(int id) {
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
        public Product updateProduct(ProductUpdateDTO productUpdateDTO, int id) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

            if(productRepository.existsByIdAndName(id, productUpdateDTO.getName()) &&
                !product.equals(productUpdateDTO)){
                throw new BusinessException("Já existe um produto com a referência e o nome informados");
            }


            productUpdateDTO.updateProduct(product, categoryRepository);
            return product;
        }

        //deleta o produto pelo id
        public void deleteProduct(int id) {

            Product product= productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
            productRepository.delete(product);
        }

}


