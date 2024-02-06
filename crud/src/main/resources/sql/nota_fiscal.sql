CREATE TABLE nota_fiscal_campos
(
    id   int NOT NULL,
    tipo int NOT NULL,
    parceiro int NOT NULL,
    numero int NOT NULL,
    data_emissao date,
    CONSTRAINT pk_nota_fiscal_campos PRIMARY KEY (id),
    CONSTRAINT fk_nota_parceiro FOREIGN KEY (parceiro) REFERENCES parceiro_negocios,
    CONSTRAINT fk_nota_tipo FOREIGN KEY (tipo) REFERENCES nota_fiscal_tipo
);

EXEC sp_rename 'nota_fiscal_campos', 'nota_fiscal';

EXEC sp_rename 'nota_fiscal.tipo' , 'id_tipo' , 'COLUMN';
EXEC sp_rename 'nota_fiscal.parceiro' , 'id_parceiro' , 'COLUMN';

ALTER TABLE nota_fiscal DROP CONSTRAINT fk_nota_parceiro;
ALTER TABLE nota_fiscal ADD CONSTRAINT fk_id_parceiro FOREIGN KEY (id_parceiro) REFERENCES parceiro_negocios(id);

ALTER TABLE nota_fiscal DROP CONSTRAINT fk_nota_tipo;
ALTER TABLE nota_fiscal ADD CONSTRAINT fk_id_tipo FOREIGN KEY (id_tipo) REFERENCES nota_fiscal_tipo(id);


ALTER TABLE nota_fiscal DROP CONSTRAINT fk_id_parceiro;
ALTER TABLE nota_fiscal ADD CONSTRAINT fk_id_parceiro FOREIGN KEY (id_parceiro) REFERENCES parceiro_negocio(id);
