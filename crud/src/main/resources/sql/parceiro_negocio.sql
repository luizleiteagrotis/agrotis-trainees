CREATE TABLE parceiro_negocio (
	id INT NOT NULL,
	nome VARCHAR(255) NOT NULL,
	inscricao_fiscal VARCHAR(255) NOT NULL,
	endereco VARCHAR(255) NOT NULL,
	telefone VARCHAR(255) NOT NULL,
	CONSTRAINT pk_parceiro_de_negocio PRIMARY KEY (id)
)