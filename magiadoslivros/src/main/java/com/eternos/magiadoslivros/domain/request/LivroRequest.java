package com.eternos.magiadoslivros.domain.request;

import com.eternos.magiadoslivros.domain.model.Livro;

import lombok.Data;
@Data
public class LivroRequest {

    String descricao;
    Integer isbn;
    String nome;
    Integer quantLivros;
    String tagEstoque;
    Double valorRecebimento;
    Double valorVenda; 
    Integer idFornecedor;

}
