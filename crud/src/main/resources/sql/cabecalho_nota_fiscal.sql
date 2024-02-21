CREATE TABLE cabecalho_nota_fiscal
{
    id   int NOT NULL PRIMARY KEY,
    tipo_nota varchar(30), 
    id_parceiro int,
    numero int,
    data date,
    CONSTRAINT fk_cabecalho_parceiro FOREIGN KEY (id_parceiro) REFERENCES cabecalho_nota_fiscal(id)
}