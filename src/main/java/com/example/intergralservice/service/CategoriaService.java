package com.example.intergralservice.service;

import java.util.List;

import com.example.intergralservice.entity.Categoria;

public interface CategoriaService {

    Categoria criar (Categoria categoria);

    List<Categoria> listarTodas();
}
