CREATE TABLE parceiro_produto
(
    id int NOT NULL,
    id_parceiro int NOT NULL,
    id_produto int NOT NULL
    CONSTRAINT pk_parceiro_produto PRIMARY KEY (id_parceiro, id_produto),
    CONSTRAINT fk_id_parceiro FOREIGN KEY(id_parceiro) REFERENCES parceiro_negocios(id),
    CONSTRAINT fk_id_produto FOREIGN KEY(id_produto) REFERENCES produto(id)
);
