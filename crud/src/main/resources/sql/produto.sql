create table produto (
       id int not null,
       custo_medio numeric,
       custo_total numeric,
        data_fabricacao date,
        data_validade date,
        descricao varchar(255),
        estoque numeric,
        nome varchar(255),
        fabricante_id int,
        primary key (id)
    )
    
    alter table produto 
       add constraint fk_fabricante_id
       foreign key (fabricante_id) 
       references parceiro_negocio
