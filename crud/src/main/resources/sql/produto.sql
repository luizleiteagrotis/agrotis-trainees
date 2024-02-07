CREATE TABLE produto (
    id INT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    id_fabricante_fornecedor INT NOT NULL,
    data_fabricacao DATE NOT NULL,
    data_validade DATE NOT NULL,
    FOREIGN KEY (id_fabricante_fornecedor) REFERENCES parceiro_negocio(id)
); 

	ALTER TABLE produto
	ADD estoque_produto int;