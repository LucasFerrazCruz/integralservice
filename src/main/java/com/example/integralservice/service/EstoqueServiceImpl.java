package com.example.integralservice.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.integralservice.config.UsuarioDetails;
import com.example.integralservice.dto.EstoqueMovimentacaoRequestDTO;
import com.example.integralservice.entity.EstoqueMovimentacao;
import com.example.integralservice.entity.Produto;
import com.example.integralservice.entity.Usuario;
import com.example.integralservice.enums.TipoMovimentacaoEstoque;
import com.example.integralservice.repository.EstoqueMovimentacaoRepository;
import com.example.integralservice.repository.ProdutoRepository;

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
    public void movimentar(EstoqueMovimentacaoRequestDTO dto, Authentication auth) {

        UsuarioDetails usuarioDetails = (UsuarioDetails) auth.getPrincipal();
        Usuario usuario = usuarioDetails.getUsuario();

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        int saldoAtual = estoqueMovimentacaoRepository.calcularSaldoPorProduto(produto.getId());

        if (dto.tipo() == TipoMovimentacaoEstoque.SAIDA && saldoAtual < dto.quantidade()) {
            throw new IllegalArgumentException("Saldo insuficiente para saída");
        }

        EstoqueMovimentacao estoqueMovimentacao = EstoqueMovimentacao.builder()
                .produto(produto)
                .tipo(dto.tipo())
                .quantidade(
                    dto.tipo() == TipoMovimentacaoEstoque.SAIDA
                        ? -dto.quantidade()
                        : dto.quantidade()
                )
                .observacao(dto.observacao())
                .usuario(usuario)
                .build();

        estoqueMovimentacaoRepository.save(estoqueMovimentacao);

    }
}
