CREATE TABLE cabecalho_nota (
	id BIGINT PRIMARY KEY IDENTITY,
	numero BIGINT,
	parceiro_id BIGINT,
	tipo_nota_id INT,
	data_emissao DATE,
	FOREIGN KEY (parceiro_id) REFERENCES parceiro_de_negocio(id),
	FOREIGN KEY (tipo_nota_id) REFERENCES nota_fiscal_tipo(id)
)