package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.Domain.User;
import com.example.dinhakids.sistemaweb.Repositorio.UserRepository;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() { return userRepository.findAll(); }

    // Verifica e cria novos usuarios
    @Transactional(rollbackOn = Exception.class)
    public User createUser(User user) {
        Optional<User> userExists = userRepository.findByUsername(user.getUsername());
        if (userExists.isPresent()) {
            throw new BusinessException("Já existe um usuário com esse username");
        }
        Optional<User> emailExists = userRepository.findByEmail(user.getEmail());
        if (emailExists.isPresent()) {
            throw new BusinessException("Já existe um usuário com esse E-mail");
        }
        userRepository.save(user);
        return user;
    }

    // procura o usuario pelo username
    public User getUserByUsername(String username) {
        Optional<User> userExists = userRepository.findByUsername(username);

        return userExists.orElseThrow(()  -> new NotFoundException("Usuário não encontrado"));
    }

    // procura o usuario pelo nome
    public User getUserByName(String nome) {
        Optional<User> userExists = userRepository.findByName(nome);

        return userExists.orElseThrow(()  -> new NotFoundException("Usuário não encontrado"));
    }

    // verifica se ja existe um username/email iguais aos que o usuario deseja atualizar
    @Transactional(rollbackOn = Exception.class)
    public User updateUser(User user) {
        Optional<User> userExists = userRepository.findByUsername(user.getUsername());
        if (userExists.isPresent() && !userExists.get().getUsername().equals(user.getUsername())) {
            throw new BusinessException("Já existe um usuario com o username informado");
        }

        Optional<User> emailExists = userRepository.findByEmail(user.getEmail());
        if (emailExists.isPresent() && !emailExists.get().getEmail().equals(user.getEmail())) {
            throw new BusinessException("Já existe um usuario com o E-mail informado");
        }

        userRepository.save(user);
        return user;
    }

    // deleta o usuario
    public void deleteUser(String username) {
        Optional<User> userExists = userRepository.findByUsername(username);

        User user = userExists.orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
        userRepository.delete(user);
    }



}