package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.DAO.FornecedorDao;
import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import com.example.dinhakids.sistemaweb.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional(readOnly = false)
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorDao dao;

    @Override
    public void salvar(Fornecedor fornecedor) {
        dao.save(fornecedor);
    }

    @Override
    public void editar(Fornecedor fornecedor) {
        dao.update(fornecedor);
    }

    @Override
    public void excluir(String id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Fornecedor buscarPorId(String id) {

        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Fornecedor> buscarTodos() {

        return dao.findAll();
    }

    @Override
    public boolean FornecedorTemProdutos(String id) {
        if (buscarPorId(id).getProducts().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public PaginacaoUtil<Fornecedor> buscarPorPagina(int pagina, String direcao) {
        return dao.buscaPaginada(pagina, direcao);
    }
}
