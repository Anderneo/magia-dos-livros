ALTER TABLE usuario
ADD CONSTRAINT UC_Usuario UNIQUE (rg,cpf);

ALTER TABLE fornecedor
ADD CONSTRAINT UC_Fornecedor UNIQUE (razao_social,cnpj);

ALTER TABLE livro
ADD CONSTRAINT UC_Livro UNIQUE (isbn);

