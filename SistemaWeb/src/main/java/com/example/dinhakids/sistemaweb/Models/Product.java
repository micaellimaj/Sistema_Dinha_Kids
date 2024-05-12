package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

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

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @CreationTimestamp
    private LocalDateTime ultimaAtualizacao;


}