package com.example.intergralservice.service;

import org.springframework.stereotype.Service;

import com.example.intergralservice.entity.EstoqueMovimentacao;
import com.example.intergralservice.entity.Produto;
import com.example.intergralservice.repository.EstoqueMovimentacaoRepository;
import com.example.intergralservice.repository.ProdutoRepository;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private final EstoqueMovimentacaoRepository estoqueMovimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueServiceImpl(EstoqueMovimentacaoRepository estoqueMovimentacaoRepository, ProdutoRepository produtoRepository) {
        this.estoqueMovimentacaoRepository = estoqueMovimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Integer consultarSaldo(Long produtoId) {
        return estoqueMovimentacaoRepository.calcularSaldoPorProduto(produtoId);
    }

    @Override
    public void movimentar(Long produtoId, Integer quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        EstoqueMovimentacao movimentacao = new EstoqueMovimentacao();
        movimentacao.setProduto(produto);
        movimentacao.setQuantidade(quantidade);

        estoqueMovimentacaoRepository.save(movimentacao);
    }
}
