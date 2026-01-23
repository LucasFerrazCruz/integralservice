package com.example.integralservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.integralservice.dto.EstoqueMovimentacaoRequestDTO;
import com.example.integralservice.service.EstoqueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    
    private final EstoqueService estoqueService;

    public EstoqueController (EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping("/movimentacoes")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void movimentar(@RequestBody @Valid EstoqueMovimentacaoRequestDTO dto, Authentication auth) {
        
        estoqueService.movimentar(dto, auth);

    }

    @GetMapping("/saldo/{produtoId}")
    @PreAuthorize("hasRole('USER')")
    public Integer consultarSaldo(@PathVariable Long produtoId) {
        return estoqueService.consultarSaldo(produtoId);
    }
}
