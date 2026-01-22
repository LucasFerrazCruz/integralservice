package com.example.intergralservice.dto;

import com.example.intergralservice.enums.TipoMovimentacaoEstoque;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EstoqueMovimentacaoRequestDTO(
    
    @NotNull(message = "Produto é obrigatório")
    Long produtoId,

    @NotNull(message = "Tipo de movimentação é obrigatória")
    TipoMovimentacaoEstoque tipo,

    @NotNull(message = "Quantidade é obrigatória")
    @Positive
    Integer quantidade,

    String observacao
) {}
