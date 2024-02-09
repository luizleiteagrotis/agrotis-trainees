CREATE TABLE produto(
    id int NOT NULL,
    nome varchar(255) NOT NULL,
    descricao varchar(255),
    fabricante int,
    data_fabricacao Date,
    data_validade Date
    
    CONSTRAINT pk_produto PRIMARY KEY (id),
    FOREIGN KEY (fabricante) REFERENCES parceiro_negocio(id),
);
