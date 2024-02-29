CREATE TABLE produto
(
    id   int NOT NULL,
    descricao varchar(255),
    fabricante int NOT NULL,
    data_fabricacao date,
    data_validade date,
    CONSTRAINT pk_produto PRIMARY KEY (id), 
    CONSTRAINT fk_fabricante FOREIGN KEY (fabricante) REFERENCES parceiro_negocios
);

ALTER TABLE produto ADD estoque INT;
EXEC sp_rename 'produto.fabricante' , 'id_parceiro' , 'COLUMN';
ALTER TABLE produto DROP CONSTRAINT fk_fabricante;
ALTER TABLE produto ADD CONSTRAINT fk_id_parceiro_produto FOREIGN KEY (id_parceiro) REFERENCES parceiro_negocio(id);

ALTER TABLE crud.dbo.produto ADD custo_medio numeric(10,2);
