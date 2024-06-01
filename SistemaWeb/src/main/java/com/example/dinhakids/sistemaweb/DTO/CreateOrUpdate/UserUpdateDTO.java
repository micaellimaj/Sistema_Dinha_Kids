package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Models.User;
import com.example.dinhakids.sistemaweb.Repository.UserRepository;
import com.example.dinhakids.sistemaweb.Services.PasswordEncoderService;
import com.example.dinhakids.sistemaweb.Services.UserService;
import com.example.dinhakids.sistemaweb.exceptions.PasswordEncodingException;
import com.example.dinhakids.sistemaweb.exceptions.UsernameAlreadyExistsException;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class UserUpdateDTO {

    @Length(max = 50, message = "Nome do usuário não pode exceder 50 caracteres")
    private String name;

    @Length(max = 30, message = "Username do usuário não pode exceder 30 caracteres")
    private String userName;

    @Email(message = "E-mail inválido")
    private String email;

    private String password;


    public void updateUser(User user, UserRepository userRepository, PasswordEncoderService passwordEncoderService) {
        if (name != null) {
            user.setName(name);
        }
        if (userName != null) {
            User existingUser = userRepository.getUserByName(userName);
            if (existingUser != null && !existingUser.getId().equals(user.getId())) {
                throw new UsernameAlreadyExistsException("Username já existe");
            }
            user.setUserName(userName);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (password != null) {
            try {
                user.setPassword(passwordEncoderService.encode(password));
            } catch (Exception e) {
                throw new PasswordEncodingException("Erro ao codificar a senha");
            }
        }
    }
}
