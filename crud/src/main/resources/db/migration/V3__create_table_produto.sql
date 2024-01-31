CREATE TABLE produto (
	id BIGINT PRIMARY KEY IDENTITY,
	nome VARCHAR(50),
	descricao VARCHAR(255),
	fabricante_id BIGINT,
	FOREIGN KEY (fabricante_id) REFERENCES parceiro_de_negocio(id)
);