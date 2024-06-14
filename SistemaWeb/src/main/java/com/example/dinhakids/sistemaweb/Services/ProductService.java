package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductCreateDTO;
import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductUpdateDTO;
import com.example.dinhakids.sistemaweb.Models.Product;
import com.example.dinhakids.sistemaweb.Repository.CategoryRepository;
import com.example.dinhakids.sistemaweb.Repository.ProductRepository;
import com.example.dinhakids.sistemaweb.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

        @Autowired
        private  ProductRepository productRepository;

        @Autowired
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

    public Product createProduct(ProductCreateDTO dto) {
        if (productRepository.existsByNameAndId(dto.getName(), dto.getId())) {
            throw new ProductAlreadyExistsException("Produto com o nome '" + dto.getName() + "' e ID '" + dto.getId() + "' já existe");
        } else if (productRepository.existsByName(dto.getName())) {
            throw new ProductAlreadyExistsException("Produto com o nome '" + dto.getName() + "' já existe");
        } else if (productRepository.existsById(dto.getId())) {
            throw new ProductAlreadyExistsException("Produto com o ID '" + dto.getId() + "' já existe");
        }

        Product product = new Product();

        product.setName(dto.getName());
        product.setId(dto.getId());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        product.setLastUpdate(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());

        if (dto.getCategory() == null || !categoryRepository.existsById(dto.getCategory().getId())) {
            throw new CategoryNotFoundException("Categoria não encontrada");
        }

        product.setCategory(dto.getCategory());

        return productRepository.save(product);
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


