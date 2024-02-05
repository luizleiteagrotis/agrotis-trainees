CREATE TABLE item_nota_fiscal(
	id BIGINT PRIMARY KEY IDENTITY,
	produto_id BIGINT,
	quantidade INT,
	preco_unitario DECIMAL(10, 2),
	cabecalho_id BIGINT,
	FOREIGN KEY (produto_id) REFERENCES produto(id),
	FOREIGN KEY (cabecalho_id) REFERENCES cabecalho_nota(id)
);