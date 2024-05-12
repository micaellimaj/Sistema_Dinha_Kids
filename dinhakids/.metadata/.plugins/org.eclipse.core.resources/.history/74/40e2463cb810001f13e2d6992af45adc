package com.example.dinhakids.sistemaweb.Controllers.DTO;

import com.example.dinhakids.sistemaweb.Models.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductCreateOrUpdateDTO {

    @NotBlank(message = "Nome do produto não informado")
    @Length(max = 100, message = "Nome do produto não pode exceder 100 caracteres")
    private String nome;

    @NotBlank(message = "id do produto não informado")
    private String id;

    private int quantidade;
    private int preco;

    public Product getProduct(){
        Product product = new Product();

        product.setNome(nome);
        product.setId(id);
        product.setQuantidade(quantidade);
        product.setPreco(preco);
        product.setUltimaAtualizacao(LocalDateTime.now());
        product.setCriadoEm(LocalDateTime.now());

        return product;
    }
}

