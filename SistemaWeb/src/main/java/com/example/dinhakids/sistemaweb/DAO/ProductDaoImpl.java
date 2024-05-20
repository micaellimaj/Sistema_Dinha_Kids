package com.example.dinhakids.sistemaweb.DAO;

import com.example.dinhakids.sistemaweb.Domain.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDaoImpl extends AbstractDao<Product, String> implements ProductDao {

    public List<Product> findByNome(String nome) {

        return createQuery("select p from Product p where p.nome like concat('%',?1,'%') ", nome);
    }

    @Override
    public List<Product> findByFornecedorId(String Id) {

        return createQuery("select p from Product p where p.fornecedor.id = ?1", Id);
    }
}
