package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.DAO.UserDao;
import com.example.dinhakids.sistemaweb.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Transactional(readOnly = false)
    @Override
    public void salvar(User user) {
        dao.save(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void editar(User user) {
        dao.update(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void excluir(UUID id) {
        dao.delete(String.valueOf(id));
    }

    @Override
    public User buscarPorId(UUID id) {
        return dao.findById(id);
    }

    @Override
    public List<User> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<User> buscarPorNome(String nome) {
        return dao.findByNome(nome);
    }

    @Override
    public List<User> buscarPorUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public List<User> buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}
