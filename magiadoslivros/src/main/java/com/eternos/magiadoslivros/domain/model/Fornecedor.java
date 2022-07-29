package com.eternos.magiadoslivros.domain.model;

import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Entity
@Table(name = "fornecedor")
public class Fornecedor extends Pessoa {
    

    @Column(name="nome_fantasia")
    private String nomeFantasia;

    @Column(name="razao_social", nullable = false)
    private String razaoSocial;

    @Column(name="cnpj", nullable = false)
    private String cnpj;

    @Column(name="ins_estadual", nullable = false)
    private String insEstadual;

    //incluirFornecedor()  void
    //atualizarFornecedor()  void
    //consultaFornecedor()  void
    //apagarFornecedor()  void
}
