CREATE TABLE parceiro_negocio
(
    id   int NOT NULL,
    nome varchar(255) NOT NULL,
    inscricao_fiscal varchar(30) NOT NULL,
    telefone varchar(20),
    endereco varchar(255),
    CONSTRAINT pk_nota_fiscal_tipo PRIMARY KEY (id)
)