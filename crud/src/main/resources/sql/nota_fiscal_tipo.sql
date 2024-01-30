CREATE TABLE parceiro_de_negocio

(

    id int,

    nome varchar(255),

    inscricao_fiscal int,

	endereco varchar(255),

	telefone varchar(255),

    CONSTRAINT pk_parceiro_negocio_tipo PRIMARY KEY (id)

)

CREATE SEQUENCE hibernate_sequence

 AS INTEGER

 START WITH 1;


CREATE TABLE nota_fiscal_tipo

(

    id  int NOT NULL,

    nome varchar(255),

    CONSTRAINT pk_nota_fiscal_tipo PRIMARY KEY (id)

);


