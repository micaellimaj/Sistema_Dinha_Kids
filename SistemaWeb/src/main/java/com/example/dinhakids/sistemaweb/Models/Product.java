package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "produtos")
public class Product {

    @Id
    private String id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private int quantidade;

    private double preco;
    private int categoria_id;
    private int fornecedor_id;

    @Temporal(TemporalType.DATE)
    private LocalDate ultimaAtualizacao;


}
