package com.example.intergralservice.service;

public interface EstoqueService {

    Integer consultarSaldo(Long produtoId);

    void movimentar(Long produtoId, Integer quantidade);
}
