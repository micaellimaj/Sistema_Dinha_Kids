package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import com.example.dinhakids.sistemaweb.util.PaginacaoUtil;

import java.util.List;

public interface FornecedorService {

    void salvar(Fornecedor fornecedor);

    void editar(Fornecedor fornecedor);

    void excluir(String id);

    Fornecedor buscarPorId(String id);

    List<Fornecedor> buscarTodos();

    boolean FornecedorTemProdutos(String id);

    PaginacaoUtil<Fornecedor> buscarPorPagina(int pagina, String direcao);
}
