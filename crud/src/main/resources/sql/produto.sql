CREATE TABLE produto
(
    id   int NOT NULL,
    descricao varchar(255),
    parceiro_negocio_fabricante varchar(255),
    data_fabricacao date,
    data_validade date,
    
    CONSTRAINT pk_produto PRIMARY KEY (id)

)