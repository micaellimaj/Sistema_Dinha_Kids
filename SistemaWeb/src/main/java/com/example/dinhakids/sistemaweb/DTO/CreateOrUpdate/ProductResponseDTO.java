package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductResponseDTO {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();

    }
}
