package com.eternos.magiadoslivros.domain.request;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.model.Livro;

public class LivroRequest {

    String descricao;
    Fornecedor fornecedor;
    Integer isbn;
    String nome;
    Integer quantLivros;
    String tagEstoque;
    Double valorRecebimento;
    Double valorVenda;
    
    
    public Livro converterClasse(){
        return Livro.builder()
        .tagEstoque(this.tagEstoque)
        .nome(this.nome)
        .descricao(this.descricao)
        .isbn(this.isbn)
        .quantLivros(this.quantLivros)
        .valorRecebimento(this.valorRecebimento)
        .valorVenda(this.valorVenda)
        .fornecedor(this.fornecedor)
        .build();
    }
}
