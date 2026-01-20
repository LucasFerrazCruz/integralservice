package com.example.intergralservice.dto;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    String codigo,
    String descricao,
    String unidade,
    Integer quantidadeAtual
) {}
