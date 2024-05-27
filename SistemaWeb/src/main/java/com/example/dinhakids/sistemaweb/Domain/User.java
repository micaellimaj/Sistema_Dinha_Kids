package com.example.dinhakids.sistemaweb.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@SuppressWarnings("serial")
@Data
@Entity(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String nome;
    private String email;
    private String senha;

    @CreationTimestamp
    private LocalDateTime CriadoEm;

}
