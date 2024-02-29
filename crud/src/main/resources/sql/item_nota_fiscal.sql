create table item_nota_fiscal (
       id int not null,
        preco_unitario numeric,
        quantidade numeric,
        valor_total numeric,
        nota_fiscal_id int,
        produto_id int,
        custo_medio numeric,
        custo_total numeric
        primary key (id)
    )
   
   alter table item_nota_fiscal 
       add constraint fk_item_nota_fiscal
       foreign key (nota_fiscal_id) 
       references nota_fiscal

   	
