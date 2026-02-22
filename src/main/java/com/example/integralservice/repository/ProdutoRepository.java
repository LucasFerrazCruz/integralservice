package com.example.integralservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.integralservice.dto.ProdutoResponseDTO;
import com.example.integralservice.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findByAtivoTrue();

    List<Produto> findByCategoriaId(Long categoriaId);

    List<Produto> findByCategoriaIdAndAtivoTrue(Long categoriaId);

    // ==========================================
    // 🔹 LISTAGEM POR CATEGORIA COM SALDO
    // ==========================================
    @Query(
        value = """
            SELECT new com.example.integralservice.dto.ProdutoResponseDTO(
                p.id,
                p.nome,
                p.codigo,
                p.descricao,
                p.unidade,
                COALESCE(
                    SUM(
                        CASE
                            WHEN e.tipo = 'ENTRADA' THEN e.quantidade
                            WHEN e.tipo = 'SAIDA' THEN -e.quantidade
                            WHEN e.tipo = 'AJUSTE' THEN e.quantidade
                            ELSE 0
                        END
                    ), 0
                )
            )
            FROM Produto p
            LEFT JOIN EstoqueMovimentacao e ON e.produto = p
            WHERE p.categoria.id = :categoriaId
            AND p.ativo = true
            GROUP BY p.id, p.nome, p.codigo, p.descricao, p.unidade
        """,
        countQuery = """
            SELECT COUNT(p)
            FROM Produto p
            WHERE p.categoria.id = :categoriaId
            AND p.ativo = true
        """
    )
    Page<ProdutoResponseDTO> buscarProdutosComSaldoPorCategoria(@Param("categoriaId") Long categoriaId, Pageable pageable);


    // ==============================
    // 🔹 LISTAGEM GERAL COM SALDO
    // ==============================

    @Query(
        value = """
            SELECT new com.example.integralservice.dto.ProdutoResponseDTO(
                p.id,
                p.nome,
                p.codigo,
                p.descricao,
                p.unidade,
                COALESCE(
                    SUM(
                        CASE
                            WHEN e.tipo = 'ENTRADA' THEN e.quantidade
                            WHEN e.tipo = 'SAIDA' THEN -e.quantidade
                            WHEN e.tipo = 'AJUSTE' THEN e.quantidade
                            ELSE 0
                        END
                    ), 0
                )
            )
            FROM Produto p
            LEFT JOIN EstoqueMovimentacao e ON e.produto = p
            WHERE p.ativo = true
            GROUP BY p.id, p.nome, p.codigo, p.descricao, p.unidade
        """,
        countQuery = """
            SELECT COUNT(p)
            FROM Produto p
            WHERE p.ativo = true
        """
    )
    Page<ProdutoResponseDTO> buscarProdutosComSaldo(Pageable pageable);
}
