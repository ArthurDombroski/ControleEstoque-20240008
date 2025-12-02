package com.controleestoque.api_estoque.dto;

import java.util.List;

public class VendaRequestDTO {

    private Long clienteId;
    private Long vendedorId;
    private List<ItemVendaRequestDTO> itens;

    public VendaRequestDTO() {
    }

    public VendaRequestDTO(Long clienteId, Long vendedorId, List<ItemVendaRequestDTO> itens) {
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.itens = itens;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public List<ItemVendaRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaRequestDTO> itens) {
        this.itens = itens;
    }
}
