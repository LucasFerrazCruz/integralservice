package com.example.intergralservice.service;

import java.util.List;

import com.example.intergralservice.entity.Produto;

public interface ProdutoService {

    Produto criar(Produto produto); 

    List<Produto> listarProdutosAtivos();

    Produto buscarPorId(Long id);
}
