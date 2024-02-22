CREATE TABLE nota_fiscal_item
(
    id INT NOT NULL,
    nota_fiscal INT NOT NULL,
    produto INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario FLOAT NOT NULL,
    valor_total FLOAT,
    CONSTRAINT pk_nota_fiscal_item PRIMARY KEY (id)
)

ALTER TABLE nota_fiscal_item 
ADD CONSTRAINT fk_produto_nota_fiscal_item
FOREIGN KEY (produto)
REFERENCES produto;

ALTER TABLE nota_fiscal_item 
ADD CONSTRAINT fk_nota_fiscal_id
FOREIGN KEY (nota_fiscal)
REFERENCES nota_fiscal(id);

ALTER TABLE nota_fiscal_item ALTER COLUMN valor_total DECIMAL(10, 2);
ALTER TABLE nota_fiscal_item ALTER COLUMN preco_unitario DECIMAL(10, 2);