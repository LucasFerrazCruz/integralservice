package com.example.intergralservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.intergralservice.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    Optional<Categoria> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);
}
