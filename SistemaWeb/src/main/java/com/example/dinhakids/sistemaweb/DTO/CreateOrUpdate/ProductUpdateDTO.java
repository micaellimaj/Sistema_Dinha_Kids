package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Models.Category;
import com.example.dinhakids.sistemaweb.Models.Product;
import com.example.dinhakids.sistemaweb.Repository.CategoryRepository;
import com.example.dinhakids.sistemaweb.exceptions.CategoryNotFoundException;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class ProductUpdateDTO {

    @Length(max = 45, message = "Nome do produto não pode exceder 45 caracteres")
    private String name;

    private String id;

    @Min(value = 0, message = "A quantidade deve ser um número inteiro e não negativo")
    private int quantity;

    @Min(value = 0, message = "O preço deve ser um número inteiro e não negativo")
    private int price;

    private int categoryId;

    public void updateProduct(Product product, CategoryRepository categoryRepository) {
        if (name != null) {
            product.setName(name);
        }
        if (id != null) {
            try {
                product.setId(Integer.parseInt(id));
            }
            catch (NumberFormatException e) {

            }
        }
        if (quantity != 0) {
            product.setQuantity(quantity);
        }
        if (price != 0) {
            product.setPrice(price);
        }
        if (categoryId != 0) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
            product.setCategory(category);
        }
    }
}
