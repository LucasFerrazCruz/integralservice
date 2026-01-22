package com.example.intergralservice.dto;

import com.example.intergralservice.enums.TipoMovimentacaoEstoque;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EstoqueMovimentacaoRequestDTO(
    
    @NotNull(message = "Produto é obrigatório")
    Long produtoId,

    @NotNull(message = "Tipo de movimentação é obrigatória")
    TipoMovimentacaoEstoque tipo,

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    Integer quantidade,

    String observacao
) {}
