    create table nota_fiscal (
       id int not null,
        data_nota date,
        numero int,
        tipo varchar(255),
        valor_total numeric,
        parceiro_negocio_id int,
        primary key (id)
    )
    
    alter table nota_fiscal 
       add constraint fk_parceiro_negocio
       foreign key (parceiro_negocio_id) 
       references parceiro_negocio
