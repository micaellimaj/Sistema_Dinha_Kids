package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.UserCreateDTO;
import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.UserUpdateDTO;
import com.example.dinhakids.sistemaweb.Models.User;
import com.example.dinhakids.sistemaweb.Repository.UserRepository;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import com.example.dinhakids.sistemaweb.exceptions.UsernameNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoderService passwordEncoderService;

    public List<User> getUsers() { return userRepository.findAll(); }

    // Verifica e cria novos usuarios
    @Transactional(rollbackOn = Exception.class)
    public User createUser(UserCreateDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoderService.encode(dto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());


        if (userRepository.findByUserName(user.getUserName()) .isPresent()) {
            throw new BusinessException("Já existe um usuário com esse username");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BusinessException("Já existe um usuário com esse E-mail");
        }

        return userRepository.save(user);
    }

    // procura o usuario pelo username
    public User getUserByUsername(String userName) {
        Optional<User> userExists = userRepository.findByUserName(userName);

        return userExists.orElseThrow(()  -> new NotFoundException("Usuário não encontrado"));
    }

    // procura o usuario pelo nome
    public User getUserByName(String name) {
        Optional<User> userExists = userRepository.findByName(name);

        return userExists.orElseThrow(()  -> new NotFoundException("Usuário não encontrado"));
    }

    // verifica se ja existe um username/email iguais aos que o usuario deseja atualizar
    @Transactional(rollbackOn = Exception.class)
    public User updateUser(String userName, UserUpdateDTO dto) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

        dto.updateUser(user, userRepository, passwordEncoderService);
        userRepository.save(user);

        return user;
    }

    // deleta o usuario
    public void deleteUser(String userName) {
        Optional<User> userExists = userRepository.findByUserName(userName);

        User user = userExists.orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
        userRepository.delete(user);
    }



}