CREATE TABLE parceiro_produto(
	idParceiro int,
	idProduto int,
	PRIMARY KEY (idParceiro, idProduto),
	FOREIGN KEY (idParceiro) REFERENCES parceiro_negocio(id),
	FOREIGN KEY (idProduto) REFERENCES produto(id)
)
