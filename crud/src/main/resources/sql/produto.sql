CREATE TABLE produto
(
    id int NOT NULL,
    nome VARCHAR(255),
    descricao varchar(510),
    parceiro_negocio INT NOT NULL,
    fabricante varchar(255),
    data_fabricacao DATE,
    data_validade DATE,
    CONSTRAINT pk_produto PRIMARY KEY (id)
);

ALTER TABLE produto
ADD CONSTRAINT fk_parceiro_negocio_id
FOREIGN KEY (parceiro_negocio)
REFERENCES parceiro_negocio;

ALTER TABLE produto
ADD estoque_produto INT;