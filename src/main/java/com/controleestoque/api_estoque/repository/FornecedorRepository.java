package com.controleestoque.api_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controleestoque.api_estoque.model.Categoria;
import com.controleestoque.api_estoque.model.Fornecedor;
import com.controleestoque.api_estoque.model.Produto;
import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    List<Fornecedor> findByNome (String nome);
    List<Fornecedor> findByNomeContaining (String parteDoNome);
}