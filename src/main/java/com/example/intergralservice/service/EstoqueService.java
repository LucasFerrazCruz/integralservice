package com.example.intergralservice.service;

import com.example.intergralservice.dto.EstoqueMovimentacaoRequestDTO;
import com.example.intergralservice.dto.EstoqueMovimentacaoResponseDTO;
import com.example.intergralservice.entity.Usuario;

public interface EstoqueService {

    Integer consultarSaldo(Long produtoId);

    public EstoqueMovimentacaoResponseDTO movimentar(EstoqueMovimentacaoRequestDTO dto, Usuario usuario);
}
