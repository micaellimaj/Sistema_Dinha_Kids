package com.example.dinhakids.sistemaweb.DAO;

import com.example.dinhakids.sistemaweb.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDaoImpl extends AbstractDao<User, String> implements UserDao {

    @Override
    public User findById(UUID id) {
        return null;
    }

    public List<User> findByNome(String nome) {

        return createQuery("select u from user p where p.nome like concat('%',?1,'%') ", nome);
    }

    @Override
    public List<User> findByUsername(String username) {

        return createQuery("select p from Product p where p.fornecedor.id = ?1", username);
    }

    @Override
    public List<User> findByEmail(String email) {
        return List.of();
    }
}
