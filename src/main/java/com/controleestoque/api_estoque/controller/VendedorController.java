package com.controleestoque.api_estoque.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import com.controleestoque.api_estoque.model.Vendedor;
import com.controleestoque.api_estoque.repository.VendedorRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor

public class VendedorController {

    private final VendedorRepository vendedorRepository;

    // Get All
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // posso fazer isso ou é só no post será?
    public List<Vendedor> getAllVendedores() {
        return vendedorRepository.findAll();
    }

    // Get id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long id) {
        return vendedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor createVendedor(@RequestBody Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    // Put
    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> updateVendedor(
            @PathVariable Long id, @RequestBody Vendedor vendedorDetails) {
        return vendedorRepository.findById(id)
                .map(vendedor -> {
                    vendedor.setNome(vendedorDetails.getNome());
                    Vendedor updatedVendedor = vendedorRepository.save(vendedor);
                    return ResponseEntity.ok(updatedVendedor);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
        if (!vendedorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vendedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
