 CREATE TABLE nota_fiscal (
       id bigint not null,
        data DATE,
        tipo varchar(255),
        numero integer,
        parceiro_negocio_id integer,
        primary key (id)
    )
    
        alter table nota_fiscal 
        add constraint fk_parceiro_negocio
        foreign key (parceiro_negocio_id) 
        references parceiro_negocio