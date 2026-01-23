package com.example.integralservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(
    @NotBlank(message = "Nome da categoria é obrigatório")
    @Size(min = 2, max = 100)
    String nome
) {}
