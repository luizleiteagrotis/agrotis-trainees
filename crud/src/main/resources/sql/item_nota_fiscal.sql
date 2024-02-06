CREATE TABLE item_nota_fiscal(
	id int NOT NULL,
	id_produto int NOT NULL,
	id_nota_fiscal int NOT NULL,
	quantidade float NOT NULL,
	preco_unitario float NOT NULL,
	valor_total float NOT NULL,
	
	CONSTRAINT pk_item_nota_fiscal PRIMARY KEY(id),
	FOREIGN KEY (id_produto) REFERENCES produto(id),
	FOREIGN KEY (id_nota_fiscal) REFERENCES nota_fiscal(id)	
	
	
)