package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Models.Product;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int category_id;


    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.category_id = product.getCategory().getId();

    }
}
