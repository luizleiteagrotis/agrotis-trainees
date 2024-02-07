CREATE TABLE item_nota_fiscal (
   		id bigint NOT NULL,
   		preco_unitario FLOAT,
   		quantidade integer,
   		valor_total FLOAT,
   		nota_fiscal_id integer,
   		produto_id integer,
   		primary key(id)
   
   )
   
   	alter table item_nota_fiscal
   	add constraint FK_item_nota_fiscal
   	foreign key (nota_fiscal_id)
   	references nota_fiscal
   	