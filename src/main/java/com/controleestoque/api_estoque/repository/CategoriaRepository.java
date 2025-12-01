package com.controleestoque.api_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controleestoque.api_estoque.model.Categoria;
import com.controleestoque.api_estoque.model.Produto;
import com.controleestoque.api_estoque.model.Fornecedor;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNome (String nome);
    List<Categoria> findByNomeContaining (String parteDoNome);
}
