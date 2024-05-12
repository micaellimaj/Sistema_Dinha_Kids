package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Fornecedores {

    @Id
    private int fornecedor_id;

    private String nome;
    private String endereco;
    private double telefone;

}
