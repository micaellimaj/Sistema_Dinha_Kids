package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity(name = "produtos")
public class Produtos {

    @Id
    private int id;
    private String nome;
    private int quantidade;
    private String descricao;
    private double preco;
    private int categoria_id;
    private int fornecedor_id;

    @CreationTimestamp
    private LocalDateTime CriadoEm;

    public Produtos(int referencia, String nome, int quantidade, String descricao, double preco, int categoria_id, int fornecedor_id) {
        this.id = referencia;
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria_id = categoria_id;
        this.fornecedor_id = fornecedor_id;
    }

    public Produtos() {

    }
}
