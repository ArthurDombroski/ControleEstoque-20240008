package com.controleestoque.api_estoque.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controleestoque.api_estoque.model.Produto;
import com.controleestoque.api_estoque.repository.CategoriaRepository;
import com.controleestoque.api_estoque.repository.FornecedorRepository;
import com.controleestoque.api_estoque.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;

    // Get All
    @GetMapping
    public List<Produto> getCategoriaById() {
        return produtoRepository.findAll();
    }

    // Get por Id
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getCategoriaById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        // Busca a categoria completa do banco
        categoriaRepository.findById(produto.getCategoria().getId())
                .ifPresent(produto::setCategoria);

        // Busca os fornecedores completos do banco
        if (produto.getFornecedores() != null && !produto.getFornecedores().isEmpty()) {
            var fornecedoresIds = produto.getFornecedores().stream()
                    .map(f -> f.getId())
                    .toList();
            
            var fornecedoresCompletos = fornecedorRepository.findAllById(fornecedoresIds);
            produto.getFornecedores().clear();
            produto.getFornecedores().addAll(fornecedoresCompletos);
        }

        // Configura o relacionamento bidirecional com Estoque
        if (produto.getEstoque() != null) {
            produto.getEstoque().setProduto(produto);
        }

        Produto savedProduto = produtoRepository.save(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }

    //Put
    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
        return produtoRepository.findById(id)
            .map(produto -> {
                produto.setNome(produtoDetails.getNome());
                Produto updatedProduto = produtoRepository.save(produto);
                return ResponseEntity.ok(updatedProduto);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if(!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
