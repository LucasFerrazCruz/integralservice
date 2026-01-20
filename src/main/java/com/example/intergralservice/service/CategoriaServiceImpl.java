package com.example.intergralservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.intergralservice.entity.Categoria;
import com.example.intergralservice.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria criar(Categoria categoria) {
        if (categoriaRepository.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new IllegalArgumentException("Categoria já existe");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }
}
