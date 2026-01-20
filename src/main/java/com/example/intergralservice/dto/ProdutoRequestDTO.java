package com.example.intergralservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoRequestDTO(

    @NotBlank(message = "Nome do produto é obrigatório.")
    @Size(max = 150)
    String nome,

    @NotBlank(message = "Código do produto é obrigatório.")
    @Size(max = 50)
    String codigo,

    String descricao,

    @NotBlank(message = "Unidade é obrigatória.")
    String unidade,

    @NotNull(message = "Categoria é obrigatória.")
    Long categoriaId
) {}
