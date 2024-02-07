create table item_nota_fiscal (
       id bigint not null,
        preco_unitario float,
        quantidade integer,
        valor_total float,
        nota_fiscal_id integer,
        produto_id integer,
        primary key (id)
    )
    
        alter table item_nota_fiscal 
       add constraint FK_nota_fiscal_id
       foreign key (nota_fiscal_id) 
       references nota_fiscal