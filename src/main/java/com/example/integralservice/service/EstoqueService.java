package com.example.integralservice.service;

import org.springframework.security.core.Authentication;

import com.example.integralservice.dto.EstoqueMovimentacaoRequestDTO;

public interface EstoqueService {

    Integer consultarSaldo(Long produtoId);

    public void movimentar(EstoqueMovimentacaoRequestDTO dto, Authentication auth);
}
