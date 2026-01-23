package com.example.integralservice.dto;

import com.example.integralservice.enums.TipoUsuario;

public record UsuarioLogadoResponseDTO(
    Long id,
    String nome,
    String email,
    TipoUsuario tipo
) {}
