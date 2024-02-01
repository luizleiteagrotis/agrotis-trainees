create table produto (
       id integer not null,
        data_fabricacao date,
        data_validade date,
        descricao varchar(255),
        fabricante_id integer,
        primary key (id)
    )
    
    ALTER TABLE produto
ADD CONSTRAINT fk_fabricante_id
FOREIGN KEY (fabricante_id)
REFERENCES parceiro_de_negocio;