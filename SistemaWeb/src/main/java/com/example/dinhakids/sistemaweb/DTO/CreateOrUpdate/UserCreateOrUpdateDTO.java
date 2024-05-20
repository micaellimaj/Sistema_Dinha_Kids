package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Domain.User;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserCreateOrUpdateDTO {

    private UUID id;

    @NotBlank(message = "Nome do usuário não informado")
    @Length(max = 50, message = "Nome do usuário não pode exceder 50 caracteres")
    private String nome;

    @NotBlank(message = "Username do usuário não informado")
    @Length(max = 30, message = "Username do usuário não pode exceder 30 caracteres")
    private String username;

    @NotBlank(message = "E-mail do usuário não informado")
    private String email;

    @NotBlank(message = "Senha do usuário não informada")
    private String senha;

    //Transforma a senha num hash via encriptador
    String passwordHashred = BCrypt.withDefaults().hashToString(12, senha.toCharArray());

    public User getUser(){
        User user = new User();

        user.setId(id);
        user.setNome(nome);
        user.setUsername(username);
        user.setEmail(email);
        user.setSenha(passwordHashred);
        user.setCriadoEm(LocalDateTime.now());

        return user;

    }

}

