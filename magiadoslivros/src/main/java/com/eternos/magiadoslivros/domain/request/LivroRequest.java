package com.eternos.magiadoslivros.domain.request;

import lombok.Data;
@Data
public class LivroRequest {

    public LivroRequest(String descricao, String isbn, String nome, Integer quantlivros, String tagestoque,
            Double valorrecebimento, Double valorvenda, Integer idfornecedor) {
    }

    public LivroRequest(){};
    
    String descricao;
    String isbn;
    String nome;
    Integer quantLivros;
    String tagEstoque;
    Double valorRecebimento;
    Double valorVenda; 
    Integer idFornecedor;

}
