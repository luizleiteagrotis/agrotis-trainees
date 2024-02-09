CREATE TABLE parceiro_negocio
(
    id int NOT NULL,
    nome varchar(255),
    inscricao_fiscal varchar(255),
    endereco varchar(255),
    telefone varchar(20),
    CONSTRAINT pk_parceiro_negocio PRIMARY KEY (id)
)