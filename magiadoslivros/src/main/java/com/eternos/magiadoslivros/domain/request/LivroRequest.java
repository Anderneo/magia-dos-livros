package com.eternos.magiadoslivros.domain.request;


import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class LivroRequest {

    String descricao;
    Integer id_fornecedor;
    Integer isbn;
    String nome;
    Integer quantLivros;
    String tagEstoque;
    Double valorRecebimento;
    Double valorVenda; 
    
    // public Fornecedor receberFornecedor(Integer id_fornecedor) {
    //     FornecedorService fornecedorService = new FornecedorService(null);
    //     return fornecedorService.buscarPorIdOuFalhar(id_fornecedor);
    // }    
    
    // public Livro converterClasse(){
    //     return Livro.builder()
    //     .tagEstoque(this.tagEstoque)
    //     .nome(this.nome)
    //     .descricao(this.descricao)
    //     .isbn(this.isbn)
    //     .quantLivros(this.quantLivros)
    //     .valorRecebimento(this.valorRecebimento)
    //     .valorVenda(this.valorVenda)
    //     .fornecedor(receberFornecedor(id_fornecedor))
    //     .build();
    // }
}
