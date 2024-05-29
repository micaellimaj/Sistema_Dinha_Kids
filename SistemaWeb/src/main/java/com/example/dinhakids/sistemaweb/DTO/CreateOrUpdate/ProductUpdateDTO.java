package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Domain.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    public void updateProduct(Product product) {
        if (name != null) {
            product.setName(name);
        }
        if (id != null) {
            product.setId(id);
        }
        if (quantity != 0) {
            product.setQuantity(quantity);
        }
        if (price != 0) {
            product.setPrice(price);
        }
    }
}
