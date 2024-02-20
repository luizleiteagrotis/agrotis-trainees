CREATE TABLE produto
(
    id   int NOT NULL PRIMARY KEY,
    descricao varchar(255),
    id_fabricante int,
    data_fabricacao date,
    data_validade date,
    CONSTRAINT fk_produto_fabricante FOREIGN KEY (id_fabricante) REFERENCES parceiro_negocio(id)
)