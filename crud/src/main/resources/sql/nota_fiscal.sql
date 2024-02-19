CREATE TABLE nota_fiscal (
    id INTEGER PRIMARY KEY,
    data_emissao DATE NOT NULL,
    numero INTEGER,
    nota_fiscal_tipo VARCHAR(255),
    parceiro_negocio_id INTEGER,
    FOREIGN KEY (parceiro_negocio_id) REFERENCES parceiro_negocio(id)
);
ALTER TABLE nota_fiscal
ADD valor_total DECIMAL(10, 2);
