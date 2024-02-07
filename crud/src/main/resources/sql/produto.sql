create table produto (
       id integer not null,
       descricao varchar(255),
       nome varchar(255),
        data_fabricacao date,
        data_validade date,
        fabricante_id integer,
        estoque int,
        primary key (id)
    )
    
    ALTER TABLE produto
ADD CONSTRAINT fk_fabricante_id
FOREIGN KEY (fabricante_id)
REFERENCES parceiro_negocio;