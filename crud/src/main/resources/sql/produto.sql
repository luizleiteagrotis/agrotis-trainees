CREATE TABLE produto
(
    id int NOT NULL,
    descricao varchar(510),
    parceiro_negocio INT NOT NULL,
    fabricante varchar(255),
    data_fabricacao DATE,
    data_validade DATE,
    FOREIGN KEY (parceiro_negocio) REFERENCES parceiro_negocio(id),
    CONSTRAINT pk_produto PRIMARY KEY (id)
)
