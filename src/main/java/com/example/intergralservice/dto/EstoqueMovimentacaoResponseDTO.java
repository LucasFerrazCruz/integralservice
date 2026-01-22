package com.example.intergralservice.dto;

import java.time.LocalDateTime;

import com.example.intergralservice.enums.TipoMovimentacaoEstoque;

public record EstoqueMovimentacaoResponseDTO(
    Long id,
    Long produtoId,
    String produtoNome,
    TipoMovimentacaoEstoque tipo,
    Integer quantidade,
    String observacao,
    LocalDateTime dataMovimentacao
) {}
