package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity(name = "usuarios")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private int id;
    private String nome;
    private String email;
    private String senha;

    public UserModel(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }


    public UserModel() {

    }
}
