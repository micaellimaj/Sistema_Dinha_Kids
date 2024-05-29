package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Domain.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductCreateDTO {

    @NotBlank(message = "Nome do produto não informado")
    @Length(max = 145, message = "Nome do produto não pode exceder 45 caracteres")
    private String name;

    @NotBlank(message = "id do produto não informado")
    private String id;

    @Min(value = 0, message = "Quantidade deve ser um número inteiro e não negativo")
    private int quantity;

    @Min(value = 0, message = "Preço deve ser um número inteiro e não negativo")
    private int price;

    public Product getProduct(){
        Product product = new Product();

        product.setName(name);
        product.setId(id);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setLastUpdate(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());

        return product;
    }
}

