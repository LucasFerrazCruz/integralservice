package com.example.intergralservice.service;

import org.springframework.stereotype.Service;

import com.example.intergralservice.dto.EstoqueMovimentacaoRequestDTO;
import com.example.intergralservice.dto.EstoqueMovimentacaoResponseDTO;
import com.example.intergralservice.entity.EstoqueMovimentacao;
import com.example.intergralservice.entity.Produto;
import com.example.intergralservice.entity.Usuario;
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
    public EstoqueMovimentacaoResponseDTO movimentar(EstoqueMovimentacaoRequestDTO dto, Usuario usuario) {

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        EstoqueMovimentacao estoqueMovimentacao = EstoqueMovimentacao.builder()
                .produto(produto)
                .tipo(dto.tipo())
                .quantidade(dto.quantidade())
                .observacao(dto.observacao())
                .usuario(usuario)
                .build();

        estoqueMovimentacaoRepository.save(estoqueMovimentacao);

        return new EstoqueMovimentacaoResponseDTO(
                estoqueMovimentacao.getId(),
                produto.getId(),
                produto.getNome(),
                estoqueMovimentacao.getTipo(),
                estoqueMovimentacao.getQuantidade(),
                estoqueMovimentacao.getObservacao(),
                estoqueMovimentacao.getDataMovimentacao()
        );
    }
}
