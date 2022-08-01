package com.eternos.magiadoslivros.domain.request;

import org.springframework.beans.factory.annotation.Autowired;

import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.service.FornecedorService;

import lombok.Data;
@Data
public class LivroRequest {

    String descricao;
    Integer id_fornecedor;
    Integer isbn;
    String nome;
    Integer quantLivros;
    String tagEstoque;
    Double valorRecebimento;
    Double valorVenda;    
    
    public Livro converterClasse(){

        FornecedorService fornecedorService = new FornecedorService();
        var fornecedor = fornecedorService.buscarPorIdOuFalhar(id_fornecedor);
    
        return Livro.builder()
        .tagEstoque(this.tagEstoque)
        .nome(this.nome)
        .descricao(this.descricao)
        .isbn(this.isbn)
        .quantLivros(this.quantLivros)
        .valorRecebimento(this.valorRecebimento)
        .valorVenda(this.valorVenda)
        .id_fornecedor(fornecedor)
        .build();
    }
}
