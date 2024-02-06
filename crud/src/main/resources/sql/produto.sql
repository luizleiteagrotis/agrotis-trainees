CREATE TABLE produto (
	id INT NOT NULL,
	id_fabricante INT NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	data_fabricacao DATE NOT NULL,
	data_validade DATE NOT NULL,
	quantidade_estoque INT,
	CONSTRAINT pk_produto PRIMARY KEY (id),
	CONSTRAINT fk_produto_parceiro FOREIGN KEY (id_parceiro_de_negocio) REFERENCES parceiro_de_negocio(id)
)
