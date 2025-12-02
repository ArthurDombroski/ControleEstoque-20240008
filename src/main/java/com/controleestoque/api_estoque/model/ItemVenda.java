package com.controleestoque.api_estoque.model;

import java.math.BigDecimal;
import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_venda")

public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantItens;
    private BigDecimal precoUnitario;


    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public ItemVenda () {

    }

    public ItemVenda(Integer quantItens, BigDecimal precoUnitario, Produto produto, Venda venda) {
        this.quantItens = quantItens;
        this.precoUnitario = precoUnitario;
        this.produto = produto;
        this.venda = venda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantItens() {
        return quantItens;
    }

    public void setQuantItens(Integer quantItens) {
        this.quantItens = quantItens;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    

}
