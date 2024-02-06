CREATE TABLE item_nota_fiscal (
    id INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    CONSTRAINT pk_item_nota_fiscal PRIMARY KEY(id),
    CONSTRAINT fk_item_fiscal_produto FOREIGN KEY (id_produto) REFERENCES produto,
);