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

    public Fornecedores(String nome, int fornecedor_id, String endereco, double telefone) {
        this.nome = nome;
        this.fornecedor_id = fornecedor_id;
        this.endereco = endereco;
        this.telefone = telefone;
    }

}
