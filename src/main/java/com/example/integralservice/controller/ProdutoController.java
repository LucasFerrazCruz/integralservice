package com.example.integralservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.integralservice.dto.ProdutoRequestDTO;
import com.example.integralservice.dto.ProdutoResponseDTO;
import com.example.integralservice.entity.Categoria;
import com.example.integralservice.entity.Produto;
import com.example.integralservice.service.ProdutoService;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final EntityManager entityManager;

    public ProdutoController(ProdutoService produtoService,
                             EntityManager entityService) {
        this.produtoService = produtoService;
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
            salvo.getQuantidadeAtual() != null 
                ? salvo.getQuantidadeAtual().longValue()
                : 0L
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    public Page<ProdutoResponseDTO> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return produtoService.listarProdutosComSaldo(pageable);
    }
}
