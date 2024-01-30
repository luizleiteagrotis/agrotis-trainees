CREATE TABLE nota_fiscal_tipo
(
    id   int NOT NULL,
    nome varchar(255),
    CONSTRAINT pk_nota_fiscal_tipo PRIMARY KEY (id)
)

CREATE TABLE parceiro_de_negocio
(
    id   int NOT NULL,
    nome varchar(255) NOT NULL,
    inscricao_fiscal varchar(255) NOT NULL,
	endereco varchar(255),
	telefone varchar(255),
    CONSTRAINT pk_parceiro_negocio_tipo PRIMARY KEY (id)
)
