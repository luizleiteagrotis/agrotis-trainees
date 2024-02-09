CREATE TABLE nota_fiscal_item
(
    id   int NOT NULL,
    produto int NOT NULL,
    quantidade int NOT NULL,
    preco_unitario decimal(10,2) NOT NULL,
    valor_total decimal(10,2),
    CONSTRAINT pk_nota_fiscal_item PRIMARY KEY (id),
    CONSTRAINT fk_nota_produto FOREIGN KEY (produto) REFERENCES produto    
);

ALTER TABLE nota_fiscal_item DROP CONSTRAINT fk_nota_produto;
EXEC sp_rename 'nota_fiscal_item.produto' , 'id_produto' , 'COLUMN';
ALTER TABLE nota_fiscal_item ADD CONSTRAINT fk_id_produto_item FOREIGN KEY (id_produto) REFERENCES produto(id);
