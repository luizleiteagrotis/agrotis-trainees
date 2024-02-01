CREATE TABLE nota_fiscal (
       id bigint not null,
        data_nota timestamp,
        nota_fiscal_tipo_id varchar(255),
        numero_nota integer,
        parceiro_de_negocio_id integer,
        primary key (id)
    )
    
    	alter table nota_fiscal 
       	add constraint FK_parceiro_negocio
       	foreign key (parceiro_de_negocio_id) 
       	references parceiro_de_negocio