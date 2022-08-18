package com.eternos.magiadoslivros.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.ISBN;

import lombok.Data;
@Data
public class LivroRequest {

    String descricao;
    
    @ISBN(message = "O Campo ISBN é inválido")
    String isbn;
    
    @NotBlank(message = "O Campo nome não pode ser vazio")
    String nome;
    
    @NotNull(message = "O Campo quantidade não pode ser vazio")
    @PositiveOrZero(message = "O Campo quantLivros deve ser um inteiro")
    Integer quantLivros;
    
    String tagEstoque;
    
    @NotNull(message = "O Campo Valor de Recebimento não pode ser vazio")
    @PositiveOrZero(message = "O Campo valorRecebimento deve ser um inteiro")
    Double valorRecebimento;
    
    @NotNull(message = "O Campo Valor de Venda não pode ser vazio")
    @PositiveOrZero(message = "O Campo valorVenda deve ser um inteiro")
    Double valorVenda; 
    
    @NotNull(message = "O Campo Id Fornecedor não pode ser vazio")
    @PositiveOrZero(message = "O Campo idFornecedor deve ser um inteiro")
    Integer idFornecedor;

}
