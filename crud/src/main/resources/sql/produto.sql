CREATE TABLE produto
(
    id   int NOT NULL,
    descricao varchar(255),
    fabricante int NOT NULL,
    data_fabricacao date,
    data_validade date,
    CONSTRAINT pk_parceiro_negocios PRIMARY KEY (id)
    CONSTRAINT fk_fabricante KEY (fabricante) REFERENCES parceiro_negocios
);
