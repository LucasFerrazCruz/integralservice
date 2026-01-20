package com.example.intergralservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.intergralservice.entity.Produto;
import com.example.intergralservice.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto criar(Produto produto) {
        produto.setAtivo(true);
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listarProdutosAtivos() {
        return produtoRepository.findByAtivoTrue();
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    }
}
