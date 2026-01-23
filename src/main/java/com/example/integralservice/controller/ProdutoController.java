package com.example.integralservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.integralservice.dto.ProdutoRequestDTO;
import com.example.integralservice.dto.ProdutoResponseDTO;
import com.example.integralservice.entity.Categoria;
import com.example.integralservice.entity.Produto;
import com.example.integralservice.service.EstoqueService;
import com.example.integralservice.service.ProdutoService;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final EstoqueService estoqueService;
    private final EntityManager entityManager;

    public ProdutoController(ProdutoService produtoService,
                             EstoqueService estoqueService,
                             EntityManager entityService) {
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
        this.entityManager = entityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ProdutoResponseDTO criar(@RequestBody @Valid ProdutoRequestDTO dto) {

        Categoria categoria = entityManager.getReference(Categoria.class, dto.categoriaId());

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setCodigo(dto.codigo());
        produto.setDescricao(dto.descricao());
        produto.setUnidade(dto.unidade());
        produto.setCategoria(categoria);

        Produto salvo = produtoService.criar(produto);

        return new ProdutoResponseDTO(
            salvo.getId(),
            salvo.getNome(),
            salvo.getCodigo(),
            salvo.getDescricao(),
            salvo.getUnidade(),
            salvo.getQuantidadeAtual()
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    public List<ProdutoResponseDTO> listar() {
        return produtoService.listarProdutosAtivos()
                .stream()
                .map(produto -> new ProdutoResponseDTO(
                    produto.getId(),
                    produto.getNome(),
                    produto.getCodigo(),
                    produto.getDescricao(),
                    produto.getUnidade(),
                    estoqueService.consultarSaldo(produto.getId())
                ))
                .toList();
    }
}
