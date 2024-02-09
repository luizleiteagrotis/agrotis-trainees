CREATE TABLE parceiro_negocios
(
    id   int NOT NULL,
    nome varchar(255),
    inscricao_fiscal varchar(255),
    endereco varchar(255),
    telefone varchar(11),
    CONSTRAINT pk_parceiro_negocios PRIMARY KEY (id)
);

EXEC sp_rename 'parceiro_negocios', 'parceiro_negocio';
