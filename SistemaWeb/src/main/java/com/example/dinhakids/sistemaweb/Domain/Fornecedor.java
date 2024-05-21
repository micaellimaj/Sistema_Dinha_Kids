package com.example.dinhakids.sistemaweb.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @NotBlank(message = "Informe um nome.")
    @Size(min = 3, max = 60, message = "o nome do fornecedor deve ter entre {min} e {max} caracteres.")
    private int nome;

    @OneToMany(mappedBy = "fornecedor")
    private List<Product> products;

    private String endereco;
    private double telefone;

}
