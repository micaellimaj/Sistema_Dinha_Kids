package com.example.dinhakids.sistemaweb.Repositorio;

import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository <Fornecedor, String> {

    public Optional<Fornecedor> findFornecedorByNome(String nome);
    public Optional<Fornecedor> findFornecedorById(String Id);
}
