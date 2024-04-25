package com.example.dinhakids.sistemaweb.Models;

import lombok.Data;

@Data
public class Categorias {
    private int id;
    private String nome;

    public Categorias(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
