package com.eternos.magiadoslivros.domain.request;

import lombok.Data;
@Data
public class LivroRequest {

    String descricao;
    Integer idFornecedor;
    Integer isbn;
    String nome;
    Integer quantLivros;
    String tagEstoque;
    Double valorRecebimento;
    Double valorVenda; 
    
}
