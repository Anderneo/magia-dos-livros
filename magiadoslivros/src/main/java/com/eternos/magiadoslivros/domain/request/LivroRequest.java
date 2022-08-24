package com.eternos.magiadoslivros.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.ISBN;

import lombok.Data;
@Data
public class LivroRequest {

    public LivroRequest(String descricao, String isbn, String nome, Integer quantlivros, String tagestoque,
            Double valorrecebimento, Double valorvenda, Integer idfornecedor) {
    }

    public LivroRequest(){};
    
    String descricao;
    
    @ISBN(message = "O Campo ISBN é inválido")
    String isbn;
    
    @NotBlank(message = "O Campo nome não pode estar vazio")
    String nome;
    
    @NotNull(message = "O Campo quantidade não pode estar vazio")
    @PositiveOrZero(message = "O Campo quantLivros deve ser um inteiro")
    Integer quantLivros;
    
    String tagEstoque;
    
    @NotNull(message = "O Campo Valor de Recebimento não pode estar vazio")
    @PositiveOrZero(message = "O Campo valorRecebimento deve ser um inteiro")
    Double valorRecebimento;
    
    @NotNull(message = "O Campo Valor de Venda não pode estar vazio")
    @PositiveOrZero(message = "O Campo valorVenda deve ser um inteiro")
    Double valorVenda; 
    
    @NotNull(message = "O Campo Id Fornecedor não pode estar vazio")
    @PositiveOrZero(message = "O Campo idFornecedor deve ser um inteiro")
    Integer idFornecedor;

}
