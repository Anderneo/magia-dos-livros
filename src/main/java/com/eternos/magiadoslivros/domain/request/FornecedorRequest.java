package com.eternos.magiadoslivros.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;

@Data
public class FornecedorRequest {

     @NotBlank(message = "O Campo descrição não pode estar vazio")
     String endereco;
     
     @Email(message = "O Campo Email é inválido")
     String email;
     
     @Pattern(regexp="^[1-9]{4}?-[0-9]{4}$", message = "O Campo telefone é inválido")
     String telefone;
     
     String observacao;
     
     @NotBlank(message = "O Campo Nome Fantasia não pode estar vazio")
     String nomeFantasia;
     
     @NotBlank(message = "O Campo Razao Social não pode estar vazio")
     String razaoSocial;
     
     @CNPJ(message = "O Campo CNPJ é inválido")
     String cnpj;
     
     @NotBlank(message = "O Campo Instituição Estadual não pode estar vazio")
     String insEstadual;
    
}
