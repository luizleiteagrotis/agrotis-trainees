CREATE TABLE produto
(
    id   int NOT NULL PRIMARY KEY,
    descricao varchar(255),
    id_fabricante int NOT NULL,
    data_fabricacao date,
    data_validade date,
    CONSTRAINT fk_id_fabricante FOREIGN KEY (id_fabricante)
    REFERENCES parceiro_negocio
)