package com.example.intergralservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intergralservice.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findByAtivoTrue();

    List<Produto> findByCategoriaId(Long categoriaId);

    List<Produto> findByCategoriaIdAndAtivoTrue(Long categoriaId);
}
