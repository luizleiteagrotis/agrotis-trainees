CREATE SEQUENCE hibernate_sequence
 AS INTEGER
 START WITH 1

CREATE TABLE parceiro_de_negocio
(
    id   int NOT NULL,
    nome varchar(255) NOT NULL,
    inscricao_fiscal varchar(255) NOT NULL,
	endereco varchar(255),
	telefone varchar(255),
    CONSTRAINT pk_parceiro_negocio_tipo PRIMARY KEY (id)
)
