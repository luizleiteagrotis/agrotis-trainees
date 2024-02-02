CREATE TABLE nota_fiscal_campos
(
    id   int NOT NULL,
    tipo int NOT NULL,
    parceiro int NOT NULL,
    numero int NOT NULL,
    data_emissao date,
    CONSTRAINT pk_nota_fiscal_campos PRIMARY KEY (id),
    CONSTRAINT fk_nota_parceiro FOREIGN KEY (parceiro) REFERENCES parceiro_negocios,
    CONSTRAINT fk_nota_tipo FOREIGN KEY (tipo) REFERENCES nota_fiscal_tipo
);
