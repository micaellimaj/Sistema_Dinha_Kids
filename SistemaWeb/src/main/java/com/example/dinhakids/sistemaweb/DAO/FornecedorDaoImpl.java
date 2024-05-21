package com.example.dinhakids.sistemaweb.DAO;

import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import com.example.dinhakids.sistemaweb.util.PaginacaoUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FornecedorDaoImpl extends AbstractDao<Fornecedor, Long> implements FornecedorDao {

    @Override
    public void delete(String id) {

    }

    @Override
    public Fornecedor findById(String id) {
        return null;
    }

    public PaginacaoUtil<Fornecedor> buscaPaginada(int pagina, String direcao) {
        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho;
        List<Fornecedor> fornecedores = getEntityManager()
                .createQuery("select f from Fornecedor order by f.nome " + direcao, Fornecedor.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, fornecedores);
    }

    public long count() {
        return getEntityManager()
                .createQuery("select count(*) from Fornecedor", long.class)
                .getSingleResult();
    }

}
