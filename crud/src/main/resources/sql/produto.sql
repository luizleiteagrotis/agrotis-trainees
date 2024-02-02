create table produto (

	id integer not null,
	data_fabricacao date,
	data_validade date,
	descricao varchar(255),
	fabricante_id integer,
	primary key (id)
)

alter table produto
add constraint fk_fabricante_id
foreign key (fabricante_id)
references parceiro_de_negocio;
);