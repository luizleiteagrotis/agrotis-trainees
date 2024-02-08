create table item_nota_fiscal (
       id int not null,
        preco_unitario double precision,
        quantidade int,
        valor_total double precision,
        nota_fiscal_id int,
        produto_id int,
        primary key (id)
    )
   
   alter table item_nota_fiscal 
       add constraint fk_item_nota_fiscal
       foreign key (nota_fiscal_id) 
       references nota_fiscal

   	