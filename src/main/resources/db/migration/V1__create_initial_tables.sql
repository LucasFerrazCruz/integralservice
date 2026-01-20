-- =========================
-- TABELA: categoria
-- =========================
CREATE TABLE categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- =========================
-- TABELA: cliente
-- =========================
CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY,
    razao_social VARCHAR(150) NOT NULL,
    nome_fantasia VARCHAR(150),
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- =========================
-- TABELA: usuario
-- =========================
CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    cliente_id BIGINT,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_usuario_cliente
        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- =========================
-- TABELA: produto
-- =========================
CREATE TABLE produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    descricao TEXT,
    categoria_id BIGINT NOT NULL,
    unidade VARCHAR(20) NOT NULL,
    quantidade_atual INT NOT NULL DEFAULT 0,
    quantidade_minima INT DEFAULT 0,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_produto_categoria
        FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

-- =========================
-- TABELA: estoque_movimentacao
-- =========================
CREATE TABLE estoque_movimentacao (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    data_movimentacao TIMESTAMP NOT NULL DEFAULT NOW(),
    observacao VARCHAR(255),
    usuario_id BIGINT NOT NULL,

    CONSTRAINT fk_movimentacao_produto
        FOREIGN KEY (produto_id) REFERENCES produto(id),

    CONSTRAINT fk_movimentacao_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- =========================
-- ÍNDICES IMPORTANTES
-- =========================
CREATE INDEX idx_produto_categoria ON produto(categoria_id);
CREATE INDEX idx_movimentacao_produto ON estoque_movimentacao(produto_id);
CREATE INDEX idx_movimentacao_usuario ON estoque_movimentacao(usuario_id);
