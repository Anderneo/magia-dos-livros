ALTER TABLE usuario(
    ADD UNIQUE (rg),
    ADD UNIQUE (cpf)
);

ALTER TABLE fornecedor(
    ADD UNIQUE (razao_social),
    ADD UNIQUE (cnpj)
);

ALTER TABLE livro
    ADD UNIQUE (isbn);
