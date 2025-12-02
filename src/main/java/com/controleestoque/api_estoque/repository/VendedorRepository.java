package com.controleestoque.api_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controleestoque.api_estoque.model.Vendedor;

import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    List<Vendedor> findByNome (String nome);
    List<Vendedor> findByNomeContaining (String parteDoNome);
}
