CREATE TABLE item_nota_fiscal
{
    id   int NOT NULL PRIMARY KEY,
    id_produto int, 
    quantidade int,
    preco_unitario decimal,
    valor_total decimal,
    CONSTRAINT fk_item_produto FOREIGN KEY (id_produto) REFERENCES produto(id)
}