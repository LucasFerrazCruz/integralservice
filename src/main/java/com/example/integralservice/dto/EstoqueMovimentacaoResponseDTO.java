package com.example.integralservice.dto;

import java.time.LocalDateTime;

import com.example.integralservice.enums.TipoMovimentacaoEstoque;

public record EstoqueMovimentacaoResponseDTO(
    Long id,
    Long produtoId,
    String produtoNome,
    TipoMovimentacaoEstoque tipo,
    Integer quantidade,
    String observacao,
    LocalDateTime dataMovimentacao
) {}
