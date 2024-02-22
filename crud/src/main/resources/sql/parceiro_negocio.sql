CREATE TABLE parceiro_negocio
(
    id INT NOT NULL,
    nome VARCHAR(255),
    inscricao_fiscal VARCHAR(255),
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    CONSTRAINT pk_parceiro_negocio PRIMARY KEY (id)
)