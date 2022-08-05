package com.eternos.magiadoslivros.domain.request;

import lombok.Data;
@Data
public class LivroRequest {

    Integer idLivro;
    String descricao;
    Integer isbn;
    String nome;
    Integer quantLivros;
    String tagEstoque;
    Double valorRecebimento;
    Double valorVenda; 
    Integer idFornecedor;
    
}
