package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Models.User;
import com.example.dinhakids.sistemaweb.Services.PasswordEncoderService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserCreateDTO {

    private UUID id;

    @NotBlank(message = "Nome do usuário não informado")
    @Length(max = 50, message = "Nome do usuário não pode exceder 50 caracteres")
    private String name;

    @NotBlank(message = "Username do usuário não informado")
    @Length(max = 30, message = "Username do usuário não pode exceder 30 caracteres")
    private String userName;

    @NotBlank(message = "E-mail do usuário não informado")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Senha do usuário não informada")
    private String password;

    private  PasswordEncoderService passwordEncoderService;

    @JsonCreator
    public UserCreateDTO(@JsonProperty("password") String password) {
        this.password = password;
    }


    public void encodePassword() {
        if (passwordEncoderService!= null) {
            this.password = passwordEncoderService.encode(password);
        }
    }

    public User createUser(){
        User user = new User();

        user.setId(id);
        user.setName(name);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(passwordEncoderService.encode(password));
        user.setCreatedAt(LocalDateTime.now());

        return user;

    }


}

