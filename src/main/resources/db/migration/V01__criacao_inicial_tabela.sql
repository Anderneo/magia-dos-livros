
CREATE SCHEMA IF NOT EXISTS livraria;

CREATE SEQUENCE usuario_seq;

CREATE TYPE genero AS ENUM ('HOMEMCIS','MULHERCIS','HOMEMTRANS','MULHERTRANS',
                            'NAOBINARIO','INTERSEXO','AGENERO','OUTRO');
                            
CREATE TYPE perfil AS ENUM ('USUARIO','ADMINISTRADOR');

CREATE TABLE IF NOT EXISTS usuario (
        id INT DEFAULT NEXTVAL ('usuario_seq'),
        nome VARCHAR(150) NOT NULL,
        endereco VARCHAR(150) NOT NULL,
        email VARCHAR(100) NOT NULL,
        telefone VARCHAR(15) NOT NULL,
        rg VARCHAR(15) NOT NULL,
        cpf VARCHAR(18) NOT NULL,
        data_de_nascimento DATE NOT NULL,
        genero genero NOT NULL,
        data_de_cadastro DATE,
        perfil perfil NOT NULL,
        observacao VARCHAR(500),
        PRIMARY KEY (id)
);


CREATE SEQUENCE pedido_seq;

CREATE TABLE IF NOT EXISTS pedido (
        id INT DEFAULT NEXTVAL ('pedido_seq'),
        valor_de_venda DOUBLE PRECISION NOT NULL,
        forma_de_pagto VARCHAR(10) NOT NULL,
        endereco_entrega VARCHAR(150) NOT NULL,
        parcela INT NOT NULL,
        data_venda DATE NOT NULL, 
        data_pagto DATE NOT NULL,
        data_entrega DATE NOT NULL,
        cancelada BOOLEAN,
        id_usuario INT NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (id_usuario)
                REFERENCES usuario (id)
                ON UPDATE RESTRICT ON DELETE CASCADE
);


CREATE SEQUENCE fornecedor_seq;

CREATE TABLE IF NOT EXISTS fornecedor (
        id INT DEFAULT NEXTVAL ('fornecedor_seq'),
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


CREATE SEQUENCE livro_seq;

CREATE TABLE IF NOT EXISTS livro (
        id INT DEFAULT NEXTVAL ('livro_seq'),
        nome VARCHAR(255) NOT NULL,
        descricao VARCHAR(150) NOT NULL,
        localizacao_estoque VARCHAR(150) NOT NULL,
        isbn VARCHAR(150) NOT NULL,
        valor_venda DOUBLE PRECISION NOT NULL,
        valor_recebimento DOUBLE PRECISION NOT NULL,
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
