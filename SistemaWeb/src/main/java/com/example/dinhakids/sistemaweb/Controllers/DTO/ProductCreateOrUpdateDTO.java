package com.example.dinhakids.sistemaweb.Controllers.DTO;

import com.example.dinhakids.sistemaweb.Models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class ProductCreateOrUpdateDTO {

    @NotBlank(message = "Nome do produto não informado")
    @Length(max = 100, message = "Nome do produto não pode exceder 100 caracteres")
    private String nome;

    @NotBlank(message = "id do produto não informado")
    private String id;

    private int quantidade;

    @NotNull(message = "id da categoria do produto não informado")
    private int categoria_id;

    @NotNull(message = "id do fornecedor do produto não informado")
    private int fornecedor_id;


    public Product getProduct(){
        Product product = new Product();

        product.setNome(nome);
        product.setId(id);
        product.setQuantidade(quantidade);
        product.setCategoria_id(categoria_id);
        product.setFornecedor_id(fornecedor_id);

        return product;
    }
}

