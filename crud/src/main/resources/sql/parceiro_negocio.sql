CREATE TABLE parceiro_negocio(
    id int NOT NULL,
    nome varchar(255) NOT NULL,
    inscricao_fiscal varchar(14) NOT NULL UNIQUE,
    endereco varchar(255),
    telefone varchar(11),
    CONSTRAINT pk_parceiro_negocio PRIMARY KEY (id)
);
