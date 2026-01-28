package com.example.integralservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.integralservice.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    Optional<Categoria> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);

}
