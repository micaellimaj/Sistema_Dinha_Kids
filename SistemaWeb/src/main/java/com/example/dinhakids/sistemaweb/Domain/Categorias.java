package com.example.dinhakids.sistemaweb.Domain;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Categorias {

    @Id
    private int categoria_id;

    private String nome;

}
