ALTER TABLE usuario
ADD CONSTRAINT UC_Person UNIQUE (rg,cpf);

ALTER TABLE fornecedor
ADD CONSTRAINT UC_Person UNIQUE (razao_social,cnpj);

ALTER TABLE livro
ADD CONSTRAINT UC_Person UNIQUE (isbn);

