package com.example.integralservice.dto;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    String codigo,
    String descricao,
    String unidade,
    Long quantidadeAtual
) {}
