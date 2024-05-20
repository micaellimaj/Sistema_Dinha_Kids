package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.Domain.Product;
import com.example.dinhakids.sistemaweb.util.PaginacaoUtil;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductService {

    void salvar(Product product);

    void editar(Product product);

    void excluir(String id);

    Product buscarPorId(String id);

    List<Product> buscarTodos();

    List<Product> buscarPorNome(String nome);

    List<Product> buscarPorFornecedor(String id);

}


