CREATE TABLE cabecalho_nota (
	id BIGINT PRIMARY KEY IDENTITY,
	numero BIGINT,
	parceiro_id BIGINT,
	tipo_nota VARCHAR(10),
	data_emissao DATE,
	valor_total DECIMAL(10, 2),
	FOREIGN KEY (parceiro_id) REFERENCES parceiro_de_negocio(id)
)