CREATE TABLE nota_fiscal(
    id int NOT NULL,
    tipo varchar(7) NOT NULL,
    id_parceiro int NOT NULL,
    numero int NOT NULL,
    data Date NOT NULL,
    valor_total float,
    
    CONSTRAINT pk_nota_fiscal PRIMARY KEY (id),
    FOREIGN KEY (id_parceiro) REFERENCES parceiro_negocio(id),      
);