package com.controleestoque.api_estoque.model;

import java.math.BigDecimal; // import para um tipo de dado no BD
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity; // import para anotações JPA
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity // Define a classe como uma entidade JPA
@Table(name = "tb_produtos") // Mapeia a entidade para a tabela "tb_produtos"

public class Produto {
    
    @Id // Define o campo como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define id como auto incremento
    private Long id; // id = auto incremento = chave primária

    private String nome;

    private BigDecimal preco;


    //Relacionamento com Estoque
    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) //orphanRemoval apaga automaticamente do banco os filhos que forem removidos da coleção do pai.
    @JsonManagedReference
    private Estoque estoque;

    //Relacionamento com Categoria
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    //Relacionamento com fornecedores
    @ManyToMany
    @JoinTable(
        name = "tb_produto_fornecedor",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private Set<Fornecedor> fornecedores;

    public Produto (){}

    public Produto(String nome, BigDecimal preco, Estoque estoque, Categoria categoria,
        Set<Fornecedor> fornecedores) {
            this.nome = nome;
            this.preco = preco;
            this.estoque = estoque;
            this.categoria = categoria;
            this.fornecedores = fornecedores;
        }

        public Long getId() {
            return id;
        }
        public void SetId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }

        public BigDecimal getPreco() {
            return preco;
        }
        public void setPreco(BigDecimal preco) {
            this.preco = preco;
        }

        public Estoque getEstoque() {
            return estoque;
        }
        public void setEstoque(Estoque estoque) {
            this.estoque = estoque;
        }

        public Categoria getCategoria() {
            return categoria;
        }
        public void setCategoria(Categoria categoria) {
            this.categoria = categoria;
        }

        public Set<Fornecedor> getFornecedores() {
            return fornecedores;
        }
        public void setFornecedores(Set<Fornecedor> fornecedores) {
            this.fornecedores = fornecedores;
        }



}
