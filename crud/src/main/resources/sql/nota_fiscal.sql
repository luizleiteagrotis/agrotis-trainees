CREATE TABLE nota_fiscal (
id INT NOT NULL,
id_parceiro_de_negocio INT NOT NULL,
id_nota_fiscal_tipo INT NOT NULL,
data DATE NOT NULL,
numero VARCHAR(255) NOT NULL,
valor_total NUMERIC(19, 2),
CONSTRAINT pk_nota_fiscal PRIMARY KEY (id),
CONSTRAINT fk_nota_fiscal_parceiro FOREIGN KEY (id_parceiro_de_negocio) REFERENCES parceiro_de_negocio(id),
CONSTRAINT fk_nota_fiscal_nota_tipo FOREIGN KEY (id_nota_fiscal_tipo) REFERENCES nota_fiscal_tipo(id),
)