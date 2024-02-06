    create table produto (
       id integer not null,
        data_fabricacao date,
        data_validade date,
        descricao varchar(255),
        quantidade_estoque integer,
        fabricante_id integer,
        primary key (id)
    )
   
    
alter table produto 
add constraint FKj2fketwmuhxv4gmwi8997xcyy 
key (fabricante_id) 
references parceiro_de_negocio