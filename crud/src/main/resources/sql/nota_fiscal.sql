CREATE TABLE nota_fiscal
(
    id INT NOT NULL,
    tipo_nota VARCHAR(10) NOT NULL,
    parceiro_negocio INT NOT NULL,
    numero_nota INT,
    data_nota DATE,
    valor_total FLOAT,
    CONSTRAINT pk_nota_fiscal PRIMARY KEY (id)
);

ALTER TABLE nota_fiscal
ADD CONSTRAINT fk_parceiro_negocio_nota_id
FOREIGN KEY (parceiro_negocio)
REFERENCES parceiro_negocio;

EXEC sp_rename 'nota_fiscal.tipo_nota', 'tipo', 'COLUMN';
EXEC sp_rename 'nota_fiscal.numero_nota', 'numero', 'COLUMN';
EXEC sp_rename 'nota_fiscal.data_nota', 'data', 'COLUMN';

ALTER TABLE nota_fiscal ALTER COLUMN valor_total DECIMAL(10, 2);