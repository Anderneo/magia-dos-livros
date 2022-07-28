package com.eternos.magiadoslivros.domain.resource;

import com.eternos.magiadoslivros.domain.model.Livro;

public class LivroRequest {

    private String tagEstoque;

    private String nome;

    private String descricao;

    private Integer isbn;

    private Integer quantLivros;

    private Double valorRecebimento;

    private Double valorVenda;

    public Livro converterClasse(){

        Livro livro = new Livro();

        livro.setTagEstoque(this.tagEstoque);
        livro.setNome(this.nome);
        livro.setDescricao(this.descricao);
        livro.setIsbn(this.isbn);
        livro.setQuantLivros(this.quantLivros);
        livro.setValorRecebimento(this.valorRecebimento);
        livro.setValorVenda(this.valorVenda);
        
        return livro;
    }
}
