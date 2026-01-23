package com.example.integralservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integralservice.dto.CategoriaRequestDTO;
import com.example.integralservice.dto.CategoriaResponseDTO;
import com.example.integralservice.entity.Categoria;
import com.example.integralservice.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public CategoriaResponseDTO criar(@RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());

        Categoria salva = categoriaService.criar(categoria);

        return new CategoriaResponseDTO(salva.getId(), salva.getNome());
    }

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        return categoriaService.listarTodas()
                .stream()
                .map(c -> new CategoriaResponseDTO(c.getId(), c.getNome()))
                .toList();
    }
}
