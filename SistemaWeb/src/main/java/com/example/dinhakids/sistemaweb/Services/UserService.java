package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.Domain.Product;
import com.example.dinhakids.sistemaweb.Domain.User;
import com.example.dinhakids.sistemaweb.Repositorio.UserRepository;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {

    void salvar(User user);

    void editar(User user);

    void excluir(UUID id);

    User buscarPorId(UUID id);

    List<User> buscarTodos();

    List<User> buscarPorNome(String nome);

    List<User> buscarPorUsername(String username);

    List<User> buscarPorEmail(String email);
}
