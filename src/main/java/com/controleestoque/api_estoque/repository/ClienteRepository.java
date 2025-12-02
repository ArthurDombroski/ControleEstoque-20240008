package com.controleestoque.api_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controleestoque.api_estoque.model.Categoria;
import com.controleestoque.api_estoque.model.Cliente;
import com.controleestoque.api_estoque.model.Produto;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome (String nome);
    List<Cliente> findByNomeContaining (String parteDoNome);
}