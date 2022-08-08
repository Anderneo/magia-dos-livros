CREATE TABLE IF NOT EXISTS usuario (
id INT AUTO_INCREMENT,
nome VARCHAR(150) NOT NULL,
endereco VARCHAR(150) NOT NULL,
email VARCHAR(100) NOT NULL,
telefone VARCHAR(15) NOT NULL,
rg VARCHAR(15) NOT NULL,
cpf VARCHAR(18) NOT NULL,
data_de_nascimento DATE NOT NULL,
genero VARCHAR(50) NOT NULL,
data_de_cadastro DATE,
perfil VARCHAR(15),
observacao VARCHAR(500),
PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pedido (
id INT AUTO_INCREMENT,
valor_de_venda DOUBLE NOT NULL,
forma_de_pagto VARCHAR(10) NOT NULL,
endereco_entrega VARCHAR(150) NOT NULL,
parcela INT NOT NULL,
data_venda DATE NOT NULL, 
data_pagto DATE NOT NULL,
data_entrega DATE NOT NULL,
cancelada BOOLEAN NOT NULL,
id_usuario INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS fornecedor (
id INT AUTO_INCREMENT,
nome_fantasia VARCHAR(255),
razao_social VARCHAR(255) NOT NULL,
cnpj VARCHAR(18) NOT NULL,
ins_estadual VARCHAR(45) NOT NULL,
endereco VARCHAR(150) NOT NULL,
telefone VARCHAR(15) NOT NULL,
email VARCHAR(100) NOT NULL,
observacao VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS livro (
id INT AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
descricao VARCHAR(150) NOT NULL,
localizacao_estoque VARCHAR(150) NOT NULL,
isbn INT NOT NULL,
valor_venda DOUBLE NOT NULL,
valor_recebimento DOUBLE NOT NULL,
quantidade_livro INT NOT NULL, 
id_fornecedor INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (id_fornecedor)
        REFERENCES fornecedor (id)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS pedido_livro (
id_livro INT,
id_pedido INT,
quantidade INT,
PRIMARY KEY (id_livro,id_pedido),
FOREIGN KEY (id_livro)
        REFERENCES livro (id)
        ON UPDATE RESTRICT ON DELETE CASCADE,
FOREIGN KEY (id_pedido)
        REFERENCES pedido (id)
        ON UPDATE RESTRICT ON DELETE CASCADE
);
