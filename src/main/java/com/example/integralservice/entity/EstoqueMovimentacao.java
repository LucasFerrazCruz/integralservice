package com.example.integralservice.entity;

import java.time.LocalDateTime;

import com.example.integralservice.enums.TipoMovimentacaoEstoque;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estoque_movimentacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1 produto pode pertencer a várias movimentações de estoque.
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacaoEstoque tipo;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDateTime dataMovimentacao;

    @Column(length = 255)
    private String observacao;

    // 1 usuário pode requisitar várias movimentações de estoque.
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @PrePersist
    public void prePersist() {
        dataMovimentacao = LocalDateTime.now();
    }
}
