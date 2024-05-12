package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Categorias {

    @Id
    private int categoria_id;
    private String nome;

    public Categorias(int categoria_id, String nome) {
        this.categoria_id = categoria_id;
        this.nome = nome;
    }

}
