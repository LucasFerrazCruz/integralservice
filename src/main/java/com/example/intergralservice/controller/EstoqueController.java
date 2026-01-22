package com.example.intergralservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.intergralservice.dto.EstoqueMovimentacaoRequestDTO;
import com.example.intergralservice.dto.EstoqueMovimentacaoResponseDTO;
import com.example.intergralservice.entity.Usuario;
import com.example.intergralservice.service.EstoqueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    
    private final EstoqueService estoqueService;

    public EstoqueController (EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping("/movimentacoes")
    public ResponseEntity<EstoqueMovimentacaoResponseDTO> movimentar(@RequestBody @Valid EstoqueMovimentacaoRequestDTO dto) {
        
        // MOCK TEMPORÁRIO -> depois vem o Spring Security
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1L);

        EstoqueMovimentacaoResponseDTO estoqueMovimentacaoResponseDTO = estoqueService.movimentar(dto, usuarioMock);

        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueMovimentacaoResponseDTO);
    }

    @GetMapping("/saldo/{produtoId}")
    public ResponseEntity<Integer> consultarSaldo(@PathVariable Long produtoId) {
        return ResponseEntity.ok(estoqueService.consultarSaldo(produtoId));
    }
}
