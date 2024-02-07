CREATE TABLE produto (
	id BIGINT PRIMARY KEY IDENTITY,
	nome VARCHAR(50),
	descricao VARCHAR(255),
	fabricante_id BIGINT,
	data_fabricacao DATE,
	data_validade DATE,
	estoque INT,
	FOREIGN KEY (fabricante_id) REFERENCES parceiro_de_negocio(id)
);