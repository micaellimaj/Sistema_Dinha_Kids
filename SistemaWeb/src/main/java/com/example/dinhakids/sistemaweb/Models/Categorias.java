package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Categorias {

    @Id
    private int categoria_id;

    private String nome;

}
