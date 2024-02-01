CREATE TABLE produto (

    id int not null,
    nome_produto varchar(255),
    descricao varchar(255),
    id_parceiro_fabricante int not null,
    data_fabricacao date not null,
    data_validade date not null,
    CONSTRAINT pk_produto PRIMARY KEY (id),
    CONSTRAINT fk_produto_parceiro FOREIGN KEY (id_parceiro_fabricante) REFERENCES parceiro_negocio(id)

);




