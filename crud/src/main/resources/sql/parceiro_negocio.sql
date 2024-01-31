CREATE TABLE parceiro_negocio
(
    id   int NOT NULL,
    nome varchar(50),
    endereco varchar(100),
    inscricao_fiscal int(20),
    telefone int (11),
    
    CONSTRAINT pk_parceiro_negocio PRIMARY KEY (id)
)