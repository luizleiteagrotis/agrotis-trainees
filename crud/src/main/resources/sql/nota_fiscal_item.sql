CREATE TABLE nota_fiscal_item (
    id INT PRIMARY KEY,
    nota_fiscal_id INT,
    produto_id INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (nota_fiscal_id) REFERENCES nota_fiscal(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);
