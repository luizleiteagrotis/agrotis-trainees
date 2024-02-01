CREATE TABLE nota_fiscal(
	id int NOT NULL,
	tipo varchar(7) NOT NULL,
	id_parceiro int NOT NULL,
	numero int NOT NULL,
	data Date NOT NULL,
	
	CONSTRAINT pk_nota_fiscal PRIMARY KEY (id),
    FOREIGN KEY (idParceiro) REFERENCES parceiro_negocio(id),		
);