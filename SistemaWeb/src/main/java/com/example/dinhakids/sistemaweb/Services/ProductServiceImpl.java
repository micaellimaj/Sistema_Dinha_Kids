package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.DAO.ProductDao;
import com.example.dinhakids.sistemaweb.Domain.Product;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Transactional(readOnly = false)
    @Override
    public void salvar(Product product) {
        dao.save(product);
    }

    @Transactional(readOnly = false)
    @Override
    public void editar(Product product) {
        dao.update(product);
    }

    @Transactional(readOnly = false)
    @Override
    public void excluir(String id) {
        dao.delete(id);
    }

    @Override
    public Product buscarPorId(String id) {
        return dao.findById(id);
    }

    @Override
    public List<Product> buscarTodos() {
        return dao.findAll();
    }

    public List<Product> buscarPorNome(String nome) {
        return dao.findByNome(nome);
    }

    public List<Product> buscarPorFornecedor(String id) {
        return dao.findByFornecedorId(id);
    }

}
