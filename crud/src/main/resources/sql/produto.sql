CREATE TABLE produto
(
    id INT NOT NULL,
    nome VARCHAR(255),
    descricao VARCHAR(510),
    parceiro_negocio INT NOT NULL,
    fabricante VARCHAR(255),
    data_fabricacao DATE,
    data_validade DATE,
    CONSTRAINT pk_produto PRIMARY KEY (id)
);

ALTER TABLE produto
ADD CONSTRAINT fk_parceiro_negocio_id
FOREIGN KEY (parceiro_negocio)
REFERENCES parceiro_negocio;

ALTER TABLE produto
ADD estoque INT;

ALTER TABLE produto
ADD custo_medio DECIMAL(10,2);