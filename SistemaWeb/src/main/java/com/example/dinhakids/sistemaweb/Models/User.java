package com.example.dinhakids.sistemaweb.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String userName;
    private String name;
    private String email;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
