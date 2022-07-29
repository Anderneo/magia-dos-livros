package com.eternos.magiadoslivros.domain.request;

import com.eternos.magiadoslivros.domain.model.Fornecedor;

public class FornecedorRequest {
    private String endereco;
    private String email;
    private String telefone;
    private String observacao;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String insEstadual;

    public Fornecedor converterClasse(){
        return Fornecedor.builder()
        .endereco(this.endereco)
        .email(this.email)
        .telefone(this.telefone)
        .observacao(this.observacao)
        .nomeFantasia(this.nomeFantasia)
        .razaoSocial(this.razaoSocial)
        .cnpj(this.cnpj)
        .insEstadual(this.insEstadual)
        .build();
    }
    
}
