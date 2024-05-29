package com.example.dinhakids.sistemaweb.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Data
@Entity(name = "produtos")
public class Product {

    @Id
    @NotBlank(message = "id do produto não informado")
    private String id;

    @NotBlank(message = "Nome do produto não informado")
    @Length(max = 100, message = "Nome do produto não pode exceder 100 caracteres")
    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantidade;

    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "###.00")
    private double preco;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @CreationTimestamp
    private LocalDateTime ultimaAtualizacao;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produtos_ibfk_2")
    private Fornecedor fornecedor;

}
