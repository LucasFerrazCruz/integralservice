package com.example.intergralservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.intergralservice.entity.EstoqueMovimentacao;

public interface EstoqueMovimentacaoRepository extends JpaRepository<EstoqueMovimentacao, Long>{

    @Query("""
        select coalesce(sum(e.quantidade), 0)
        from EstoqueMovimentacao e
        where e.produto.id = :produtoId
    """)

    Integer calcularSaldoPorProduto(@Param("produtoId") Long produtoId);
}
