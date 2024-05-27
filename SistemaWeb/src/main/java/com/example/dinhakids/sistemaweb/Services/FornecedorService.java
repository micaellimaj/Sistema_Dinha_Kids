package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import com.example.dinhakids.sistemaweb.Repositorio.FornecedorRepository;
import com.example.dinhakids.sistemaweb.exceptions.BusinessException;
import com.example.dinhakids.sistemaweb.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

        @Autowired
        private FornecedorRepository fornecedorRepository;

        public List<Fornecedor> getFornecedores(){
            return fornecedorRepository.findAll();
        }


        //verifica e cria novos fornecedores
        @Transactional(rollbackOn = Exception.class)
        public Fornecedor createFornecedor(Fornecedor fornecedor){
            Optional<Fornecedor> FornecedorNameExists = fornecedorRepository.findFornecedorByNome(fornecedor.getNome());
            if(FornecedorNameExists.isPresent()){
                throw new BusinessException("Já existe um fornecedo com o Nome informado");
            }

            Optional<Fornecedor> IdExists = fornecedorRepository.findFornecedorById(fornecedor.getId());
            if(IdExists.isPresent()){
                throw new BusinessException("Já existe um fornecedor com o ID informado");
            }

            fornecedorRepository.save(fornecedor);
            return fornecedor;
        }


        //procura o fornecedor pelo id
        public Fornecedor getFornecedorById(String id) {
            Optional<Fornecedor> fornecedorExists = fornecedorRepository.findFornecedorById(id);

            return fornecedorExists.orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));
        }

        //procura o fornecedor pelo nome
        public Fornecedor getFornecedorByName(String nome) {
        Optional<Fornecedor> fornecedorExists = fornecedorRepository.findFornecedorByNome(nome);

        return fornecedorExists.orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));
        }

        //atualiza o fornecedor
        @Transactional(rollbackOn = Exception.class)
        public Fornecedor updateFornecedor(Fornecedor fornecedor) {
            Optional<Fornecedor> idExists = fornecedorRepository.findFornecedorByNome(fornecedor.getNome());
            if(idExists.isPresent() && !idExists.get().getId().equals(fornecedor.getId())){
                throw new BusinessException("Já existe um fornecedor com o id informado");
            }

            Optional<Fornecedor> NameExists = fornecedorRepository.findFornecedorByNome(fornecedor.getNome());
            if(NameExists.isPresent() && !NameExists.get().getId().equals(fornecedor.getId())){
                throw new BusinessException("Já existe um fornecedor com o nome informado");
            }

            fornecedorRepository.save(fornecedor);
            return fornecedor;
        }

        //deleta o fornecedor pelo id
        public void deleteFornecedor(String id) {
            Optional<Fornecedor> fornecedorExists = fornecedorRepository.findById(id);

            Fornecedor fornecedor = fornecedorExists.orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));
            fornecedorRepository.delete(fornecedorExists.get());
        }

    }
