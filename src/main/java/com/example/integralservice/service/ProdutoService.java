package com.example.integralservice.service;

import java.util.List;

import com.example.integralservice.entity.Produto;

public interface ProdutoService {

    Produto criar(Produto produto); 

    List<Produto> listarProdutosAtivos();

    Produto buscarPorId(Long id);
}
