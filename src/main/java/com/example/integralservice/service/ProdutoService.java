package com.example.integralservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.integralservice.dto.ProdutoResponseDTO;
import com.example.integralservice.entity.Produto;

public interface ProdutoService {

    Produto criar(Produto produto); 

    List<Produto> listarProdutosAtivos();

    Produto buscarPorId(Long id);

    Page<ProdutoResponseDTO> listarComSaldoPorCategoria(Long categoriaId, Pageable pageable);

    Page<ProdutoResponseDTO> listarProdutosComSaldo(Pageable pageable);
}
