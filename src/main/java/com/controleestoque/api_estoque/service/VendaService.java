package com.controleestoque.api_estoque.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controleestoque.api_estoque.dto.VendaRequestDTO;
import com.controleestoque.api_estoque.dto.ItemVendaRequestDTO;
import com.controleestoque.api_estoque.model.Cliente;
import com.controleestoque.api_estoque.model.Estoque;
import com.controleestoque.api_estoque.model.ItemVenda;
import com.controleestoque.api_estoque.model.Produto;
import com.controleestoque.api_estoque.model.Venda;
import com.controleestoque.api_estoque.model.Vendedor;
import com.controleestoque.api_estoque.repository.ClienteRepository;
import com.controleestoque.api_estoque.repository.ProdutoRepository;
import com.controleestoque.api_estoque.repository.VendaRepository;
import com.controleestoque.api_estoque.repository.VendedorRepository;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(
        VendaRepository vendaRepository,
        ClienteRepository clienteRepository,
        VendedorRepository vendedorRepository,
        ProdutoRepository produtoRepository
    ) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Venda registrarVenda(VendaRequestDTO dto) {

        Cliente cliente = buscarCliente(dto.getClienteId());
        Vendedor vendedor = buscarVendedor(dto.getVendedorId());

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setVendedor(vendedor);
        venda.setPrecoTotal(BigDecimal.ZERO);

        List<ItemVenda> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ItemVendaRequestDTO itemDTO : dto.getItens()) {
            ItemProcessado itemProcessado = processarItem(venda, itemDTO);
            itens.add(itemProcessado.itemVenda());
            total = total.add(itemProcessado.subtotal());
        }

        venda.setItens(itens);
        venda.setPrecoTotal(total);

        return vendaRepository.save(venda);
    }

    // ================= MÉTODOS PRIVADOS DE APOIO =================

    private Cliente buscarCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + clienteId));
    }

    private Vendedor buscarVendedor(Long vendedorId) {
        return vendedorRepository.findById(vendedorId)
            .orElseThrow(() -> new IllegalArgumentException("Vendedor não encontrado: " + vendedorId));
    }

    private ItemProcessado processarItem(Venda venda, ItemVendaRequestDTO itemDTO) {

        Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + itemDTO.getProdutoId()));

        Estoque estoque = produto.getEstoque();
        if (estoque == null) {
            throw new IllegalArgumentException("Produto sem estoque cadastrado: " + produto.getNome());
        }

        Integer quantidadeSolicitada = itemDTO.getQuantidade();
        Integer quantidadeDisponivel = estoque.getQuantidade();

        if (quantidadeDisponivel < quantidadeSolicitada) {
            throw new IllegalArgumentException(
                "Estoque insuficiente para o produto: " + produto.getNome() +
                ". Disponível: " + quantidadeDisponivel +
                ", solicitado: " + quantidadeSolicitada
            );
        }

        // baixa de estoque
        estoque.setQuantidade(quantidadeDisponivel - quantidadeSolicitada);

        BigDecimal precoUnitario = produto.getPreco();
        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidadeSolicitada));

        // aqui vou usar o construtor completo do ItemVenda,
        // pra nem depender de setter se der problema de cache
        ItemVenda itemVenda = new ItemVenda(
            quantidadeSolicitada,
            precoUnitario,
            produto,
            venda
        );

        return new ItemProcessado(itemVenda, subtotal);
    }

    // "record" interno só para juntar o ItemVenda com o subtotal calculado
    private record ItemProcessado(ItemVenda itemVenda, BigDecimal subtotal) {}
}
