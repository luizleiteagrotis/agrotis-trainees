CREATE TABLE nota_fiscal_tipo
(
    id INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_emissao DATE NOT NULL, 
    nota_fiscal_id INT,
);