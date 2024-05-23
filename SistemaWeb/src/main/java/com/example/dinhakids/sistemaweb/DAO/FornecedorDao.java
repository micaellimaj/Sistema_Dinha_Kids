package com.example.dinhakids.sistemaweb.DAO;

import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import com.example.dinhakids.sistemaweb.util.PaginacaoUtil;

import java.util.List;

public interface FornecedorDao {

    void save(Fornecedor fornecedor);

    void update(Fornecedor fornecedor);

    void delete(String id);

    Fornecedor findById(String id);

    List<Fornecedor> findAll();

    PaginacaoUtil<Fornecedor> buscaPaginada(int pagina, String direcao);
}
