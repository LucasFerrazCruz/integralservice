package com.example.integralservice.service;

import java.util.List;

import com.example.integralservice.entity.Categoria;

public interface CategoriaService {

    Categoria criar (Categoria categoria);

    List<Categoria> listarTodas();

    Categoria buscarCategoriaPorId(Long id);
}
