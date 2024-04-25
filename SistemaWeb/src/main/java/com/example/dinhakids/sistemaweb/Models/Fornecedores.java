package com.example.dinhakids.sistemaweb.Models;

import lombok.Data;

@Data
public class Fornecedores {
    private int id;
    private String nome;
    private String endereco;
    private double telefone;

    public Fornecedores(String nome, int id, String endereco, double telefone) {
        this.nome = nome;
        this.id = id;
        this.endereco = endereco;
        this.telefone = telefone;
    }

}
