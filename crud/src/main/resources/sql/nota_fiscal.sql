CREATE TABLE nota_fiscal
(
    id int NOT NULL,
    tipo_nota INT NOT NULL,
    parceiro_negocio INT NOT NULL,
    numero_nota INT,
    data_nota DATE,
    CONSTRAINT pk_nota_fiscal PRIMARY KEY (id)
);

ALTER TABLE nota_fiscal
ADD CONSTRAINT fk_parceiro_negocio_nota_id
FOREIGN KEY (parceiro_negocio)
REFERENCES parceiro_negocio;

ALTER TABLE nota_fiscal
ADD CONSTRAINT fk_tipo_nota_id
FOREIGN KEY (tipo_nota)
REFERENCES nota_fiscal_tipo;