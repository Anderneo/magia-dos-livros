package com.eternos.magiadoslivros.domain.request;

import lombok.Data;

@Data
public class FornecedorRequest {
     String endereco;
     String email;
     String telefone;
     String observacao;
     String nomeFantasia;
     String razaoSocial;
     String cnpj;
     String insEstadual;
    
}
